SUMMARY = "XM200C Filesystem Tree"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://machineID \
           file://machineProduct \
           file://screenResolution \
           "

FILES_${PN} += " \
           /usr/share/xact \
           /var/db/xact \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
# Install binary to final directory /usr/bin
do_install() {
    install -d ${D}/var/db/xact
    install -d ${D}/usr/share/xact
    install -d ${D}/etc/xact
    install -m 0644 ${S}/machineID ${D}/etc/xact
    install -m 0644 ${S}/machineProduct ${D}/etc/xact
    install -m 0644 ${S}/screenResolution ${D}/etc/xact
}

