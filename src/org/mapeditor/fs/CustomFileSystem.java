package org.mapeditor.fs;

import mgi.tools.rseditor.core.cache.Cache;

public class CustomFileSystem {
	
	public static Cache cache;
	
	static {
		cache = Cache.openCache("./cache/");
	}

}
