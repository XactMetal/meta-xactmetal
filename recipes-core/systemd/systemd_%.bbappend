
pkg_postinst:udev-hwdb () {
	if test -n "$D"; then
		$INTERCEPT_DIR/postinst_intercept update_udev_hwdb ${PKG} mlprefix=${MLPREFIX} binprefix=${MLPREFIX} rootlibexecdir="${rootlibexecdir}" PREFERRED_PROVIDER_udev="${PREFERRED_PROVIDER_udev}" base_bindir="${base_bindir}"
	fi
}
