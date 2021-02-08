SUMMARY = "XM200C"
DESCRIPTION = "TorizonCore Linux with XM200C dependencies."

require xact-common.inc

CORE_IMAGE_BASE_INSTALL_append = " \
    docker-ce \
    python3-docker-compose \
    docker-compose-up \
"

inherit extrausers

EXTRA_USERS_PARAMS += "\
usermod -a -G docker torizon; \
"
