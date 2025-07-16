FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Prevent inclusion of default defconfig
unset KBUILD_DEFCONFIG

SRC_URI += "\
    file://0000-add-smsc95xx-mac-param.patch \
    file://defconfig \
    "
