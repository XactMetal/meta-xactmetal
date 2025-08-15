FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "\
    file://xact.cfg \
    file://0000-add-smsc95xx-mac-param.patch \
"
