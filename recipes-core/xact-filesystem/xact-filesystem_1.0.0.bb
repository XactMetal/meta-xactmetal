SUMMARY = "XM200C Filesystem Tree"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://machineID \
           file://machineProduct \
           file://screenResolution \
           file://host_platform \
           file://image_version \
           file://xact-run \
           file://xact-init \
           file://xact-init-img \
           "

FILES_${PN} += " \
           /usr/share/xact \
           /run/xact \
           /run/xact/ssd \
           /run/xact/card1 \
           /usr/local/bin \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
# Install binary to final directory /usr/bin
do_install() {
    install -d ${D}/run/xact
    install -d ${D}/run/xact/ssd
    install -d ${D}/run/xact/card1
    install -d ${D}/usr/share/xact
    install -d ${D}/usr/local/bin
    install -d ${D}/etc/xact
    install -m 0644 ${S}/machineID ${D}/etc/xact
    install -m 0644 ${S}/machineProduct ${D}/etc/xact
    install -m 0644 ${S}/screenResolution ${D}/etc/xact
    install -m 0644 ${S}/host_platform ${D}/etc/xact
    install -m 0644 ${S}/image_version ${D}/etc/xact
    install -m 0755 ${S}/xact-run ${D}/usr/local/bin
    install -m 0755 ${S}/xact-init ${D}/usr/local/bin
    install -m 0755 ${S}/xact-init-img ${D}/usr/local/bin
}

