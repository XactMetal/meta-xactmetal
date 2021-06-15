SUMMARY = "XM200C"
DESCRIPTION = "TorizonCore Linux with XM200C dependencies."

IMAGE_VARIANT = "xm200C"

XACT_FS_RESOLUTION = "1024x600"
XACT_MACHINE_ID = "XM200C_Ext"
XACT_MACHINE_PRODUCT = "XM200C"

require xact-common.inc

CORE_IMAGE_BASE_INSTALL_append = " \
    docker-ce \
    python3-docker-compose \
    docker-compose-up \
    xact-filesystem \
"

inherit extrausers

EXTRA_USERS_PARAMS += "\
  usermod -a -G docker torizon; \
  "

