SUMMARY = "Xact Filesystem Tree"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN}="bash"

SRC_URI = " \
           file://xact-run \
           file://xact-init \
           file://xact-init-img \
           "

FILES_${PN} += " \
           /usr/share/xact \
           /mnt/xact/card1 \
           /mnt/xact/ssd \
           /etc/xact \
           ${bindir}/xact-run \
           ${bindir}/xact-init \
           ${bindir}/xact-init-img \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
# Install binary to final directory /usr/bin
do_install() {
    install -d ${D}/usr/share/xact
    install -d ${D}/mnt/xact/card1
    install -d ${D}/mnt/xact/ssd
    install -d ${D}/etc/xact
    install -d ${D}${bindir}
    install -m 0755 ${S}/xact-run ${D}${bindir}
    install -m 0755 ${S}/xact-init ${D}${bindir}
    install -m 0755 ${S}/xact-init-img ${D}${bindir}
}

