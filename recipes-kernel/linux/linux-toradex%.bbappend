FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Prevent inclusion of default defconfig
unset KBUILD_DEFCONFIG

# Remove defconfig for now
#    file://defconfig 

SRC_URI += "\
    file://0000-add-smsc95xx-mac-param.patch \
    "
