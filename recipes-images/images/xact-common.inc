LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit core-image

do_rootfs[cleandirs] += "${IMAGE_ROOTFS}"

TEZI_IMAGE_NAME = "${IMAGE_BASENAME}${IMAGE_BASENAME_SUFFIX}-${MACHINE}"
IMAGE_NAME = "${IMAGE_BASENAME}${IMAGE_BASENAME_SUFFIX}-${MACHINE}${IMAGE_VERSION_SUFFIX}"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}${IMAGE_BASENAME_SUFFIX}-${MACHINE}"

IMAGE_FEATURES += "ssh-server-openssh bash-completion-pkgs"

XACT_IMAGE_VERSION = "1"

# Base packages
CORE_IMAGE_BASE_INSTALL_append = " \
    kernel-modules \
    usermount \
    nano \
    set-hostname \
    systemd-analyze \
    sudo \
    torizon-conf \
    torizon-users \
    tzdata \
    evtest \
    i2c-tools \
    udev-toradex-rules \
    network-configuration \
    ostree-devicetree-overlays \
    kernel-module-imx-gpu-viv \
    cpufrequtils \
    curl \
    htop \
    jq \
    ldd \
    less \
    openssh-sftp-server \
    packagegroup-core-full-cmdline-multiuser \
    ostree-devicetree-overlays \
    packagegroup-core-full-cmdline-utils \
    rsync \
    sshfs-fuse \
    strace \
    tcpdump \
    vim \
    zram \
    git \
    screen \
    kernel-devsrc \
    kernel-module-wireguard \
    wireguard-tools \
    fluent-bit \
    neofetch \
"

# Enough free space for a full image update
IMAGE_OVERHEAD_FACTOR = "2.3"

nss_altfiles_set_users_groups () {
	# Make a temporary directory to be used by pseudo to find the real /etc/passwd,/etc/group
	pseudo_dir=${WORKDIR}/pseudo-rootfs${sysconfdir}
	override_dir=${IMAGE_ROOTFS}${sysconfdir}
	nsswitch_conf=${IMAGE_ROOTFS}${sysconfdir}/nsswitch.conf

	sed -i -e '/^passwd/s/$/ altfiles/' -e '/^group/s/$/ altfiles/' -e '/^shadow/s/$/ altfiles/' ${nsswitch_conf}

	install -d ${pseudo_dir}
	install -m 644 ${override_dir}/passwd ${pseudo_dir}
	install -m 644 ${override_dir}/group ${pseudo_dir}
	install -m 400 ${override_dir}/shadow ${pseudo_dir}
	cp -a ${pseudo_dir}/* ${IMAGE_ROOTFS}${libdir}

	for file in passwd group shadow; do
		cat > ${override_dir}/${file} <<- EOF
			# NSS altfiles module is installed. Default user, group and shadow files are in
			# /usr/lib/
		EOF
		grep -r torizon ${IMAGE_ROOTFS}${libdir}/${file} >> ${override_dir}/${file}
	done
}

check_xact_filesystem () {
	echo "TA_iMX8_Linux" > ${IMAGE_ROOTFS}${sysconfdir}/xact/host_platform
	echo ${XACT_IMAGE_VERSION} > ${IMAGE_ROOTFS}${sysconfdir}/xact/image_version

        if [ -n "${XACT_MACHINE_PRODUCT}" ]; then
	        echo ${XACT_MACHINE_PRODUCT} > ${IMAGE_ROOTFS}${sysconfdir}/xact/machineProduct
	else
                bbwarn "XACT_MACHINE_PRODUCT is missing, must be defined in xm*.bb"
        fi

        if [ -n "${XACT_MACHINE_ID}" ]; then
	        echo ${XACT_MACHINE_ID} > ${IMAGE_ROOTFS}${sysconfdir}/xact/machineID
	else
                bbwarn "XACT_MACHINE_ID is missing, must be defined in xm*.bb"
        fi

        if [ -n "${XACT_FS_RESOLUTION}" ]; then
	        echo ${XACT_FS_RESOLUTION} > ${IMAGE_ROOTFS}${sysconfdir}/xact/screenResolution
	else
                bbwarn "XACT_FS_RESOLUTION is missing, must be defined in xm*.bb"
        fi
        
	# Add fstab entries
	echo "/dev/disk/by-path/platform-5b020000.mmc-part1 /usr/share/xact/card1 auto defaults,sync,noauto 0 0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
        echo "/dev/disk/by-path/platform-5f020000.sata-ata-1-part1 /usr/share/xact/ssd/ ext4 auto,defaults,noatime,noauto 0 0" >> ${IMAGE_ROOTFS}${sysconfdir}/fstab
}

ROOTFS_POSTPROCESS_COMMAND += "check_xact_filesystem ; "

CORE_IMAGE_BASE_INSTALL_append_mx8 = " \
    kernel-module-imx-gpu-viv \
"

OSTREE_KERNEL_ARGS = "quiet logo.nologo console=ttyLP1 vt.global_cursor_default=0 plymouth.ignore-serial-consoles splash"

# include nss-altfiles support
CORE_IMAGE_BASE_INSTALL_append = ' ${@bb.utils.contains("DISTRO_FEATURES", "stateless-system", "nss-altfiles", "",d)}'
IMAGE_PREPROCESS_COMMAND_append = ' ${@bb.utils.contains("DISTRO_FEATURES", "stateless-system", "nss_altfiles_set_users_groups; ", "",d)}'
PSEUDO_PASSWD_prepend = "${@bb.utils.contains('DISTRO_FEATURES', 'stateless-system', '${WORKDIR}/pseudo-rootfs:', '', d)}"

# due to limited hardware resources, remove Colibri iMX6 Solo 256MB
# from the list of supported IDs in the Tezi image
TORADEX_PRODUCT_IDS_remove_colibri-imx6 = "0014 0016"
