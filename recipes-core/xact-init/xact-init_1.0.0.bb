SUMMARY = "Xact init script for systemd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN}="bash"

SRC_URI = " \
           file://xact-init.service \
           file://xact-splash.service \
           file://xact-init-img \
           file://xact-init \
           file://xact-run \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
do_install() {
  install -d ${D}${bindir}
  install -m 0755 ${S}/xact-run ${D}${bindir}
  install -m 0755 ${S}/xact-init ${D}${bindir}
  install -m 0755 ${S}/xact-init-img ${D}${bindir}
  install -D -p -m0644 ${WORKDIR}/xact-init.service ${D}${systemd_system_unitdir}/xact-init.service
  install -D -p -m0644 ${WORKDIR}/xact-splash.service ${D}${systemd_system_unitdir}/xact-splash.service
}

inherit features_check systemd

FILES_${PN} += "{systemd_system_unitdir}/xact-init.service \
           {systemd_system_unitdir}/xact-splash.service \
           ${bindir}/xact-run \
           ${bindir}/xact-run \
           ${bindir}/xact-init \
           ${bindir}/xact-init-img \
           "

SYSTEMD_SERVICE_${PN} = "xact-init.service xact-splash.service"
SYSTEMD_AUTO_ENABLE = "enable"
