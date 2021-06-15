
OSTREE_DEPLOY_DEVICETREE = "1"

# Cross machines / BSPs
## Drop IMX BSP that is not needed
MACHINE_EXTRA_RRECOMMENDS_remove_imx = "imx-alsa-plugins"

# SDK_VERSION should not contain ${TDX_PRERELEASE} (DATETIME),
# or else it might lead to mismatch paths when installing
# target packages and host packages.
SDK_VERSION = "${TDX_RELEASE}"

# git is required by torizon to get hashes from all meta layers ()
HOSTTOOLS += "git"

