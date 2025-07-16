SUMMARY = "XM200C"
DESCRIPTION = "TorizonCore Linux with XM200C dependencies."

IMAGE_VARIANT = "xm200C"

XACT_FS_RESOLUTION = "1024x600"
XACT_MACHINE_ID = "XM200C_Ext"
XACT_MACHINE_PRODUCT = "XM200C"

VIRTUAL-RUNTIME_container_engine = "docker"

require xact-common.inc

CORE_IMAGE_BASE_INSTALL:append = " \
    xact-filesystem \
    xact-init \
"

inherit extrausers

EXTRA_USERS_PARAMS += "\
  usermod -a -G docker torizon; \
  "

