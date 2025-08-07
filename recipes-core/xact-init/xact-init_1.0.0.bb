SUMMARY = "Xact init script for systemd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN}="bash python3-core python3-mmap"

SRC_URI = " \
           file://xact-init.service \
           file://xact-splash.service \
           file://init-journal.service \
           file://run-logp.mount \
           file://10-xact-tmpfiles.conf \
           file://00-xact-journald.conf \
           file://xact-init-img \
           file://xact-init \
           file://xact-run \
           file://xact-commands-txt.py \
           "

# Change directory to sources 
S = "${WORKDIR}"
 
do_compile() {
}
 
do_install() {
  install -d ${D}${bindir}
  install -d ${D}${sysconfdir}
  install -d ${D}${sysconfdir}/tmpfiles.d
  install -d ${D}${sysconfdir}/systemd
  install -d ${D}${sysconfdir}/systemd/journald.conf.d
  install -m 0755 ${S}/xact-run ${D}${bindir}
  install -m 0755 ${S}/xact-init ${D}${bindir}
  install -m 0755 ${S}/xact-init-img ${D}${bindir}
  install -m 0755 ${S}/xact-commands-txt.py ${D}${bindir}
  install -m 0644 ${S}/10-xact-tmpfiles.conf ${D}${sysconfdir}/tmpfiles.d
  install -m 0644 ${S}/00-xact-journald.conf ${D}${sysconfdir}/systemd/journald.conf.d
  install -D -p -m0644 ${WORKDIR}/xact-init.service ${D}${systemd_system_unitdir}/xact-init.service
  install -D -p -m0644 ${WORKDIR}/xact-splash.service ${D}${systemd_system_unitdir}/xact-splash.service
  install -D -p -m0644 ${WORKDIR}/init-journal.service ${D}${systemd_system_unitdir}/init-journal.service
  install -D -p -m0644 ${WORKDIR}/run-logp.mount ${D}${systemd_system_unitdir}/run-logp.mount
}

inherit features_check systemd

FILES:${PN} += "${sysconfdir} \
           ${sysconfdir}/systemd \
           ${systemd_system_unitdir} \
           ${systemd_system_unitdir}/xact-init.service \
           ${systemd_system_unitdir}/xact-splash.service \
           ${systemd_system_unitdir}/init-journal.service \
           ${systemd_system_unitdir}/run-logp.mount \
           ${sysconfdir}/tmpfiles.d/10-xact-tmpfiles.conf \
           ${sysconfdir}/systemd/journald.conf.d/00-xact-journald.conf \
           ${bindir}/xact-commands-txt.py \
           ${bindir}/xact-run \
           ${bindir}/xact-init \
           ${bindir}/xact-init-img \
           "

SYSTEMD_SERVICE:${PN} = "xact-init.service xact-splash.service init-journal.service run-logp.mount"
SYSTEMD_AUTO_ENABLE = "enable"
