SUMMARY = "Xact Filesystem Tree"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN}="bash"
FILES:${PN} += " \
           /usr/share/xact \
           /usr/share/xact/card1 \
           /usr/share/ssd \
           /etc/xact \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
# Install binary to final directory /usr/bin
do_install() {
    install -d ${D}/usr/share/xact
    install -d ${D}/usr/share/xact/card1
    install -d ${D}/usr/share/xact/ssd
    install -d ${D}/etc/xact
}

