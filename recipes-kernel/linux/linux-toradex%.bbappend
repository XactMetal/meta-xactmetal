FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:common-torizon = "\
    file://xact.scc \
    file://xact.cfg \
"

SRC_URI += "\
    file://0000-add-smsc95xx-mac-param.patch \
    "
