/**
 * BS 9.3 
 * 
 * */
package pkg_9;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

/**
 * Describes a file by its attributes 
 * 
 * */
public class FileAttributes {

	private Path path;
	private boolean exists;
	private boolean isDirectory;
	private boolean isExecutable;
	private boolean isHidden;
	private long sizeInBytes;
	private FileTime lastModifiedTime;
	private UserPrincipal owner;
	
	private String exceptionMessage;
	
	public FileAttributes( Path path, boolean followSymbolicLinks ) {
		this.path = path;
		this.exists = Files.exists(path, ( followSymbolicLinks ? null : LinkOption.NOFOLLOW_LINKS ));
		if ( this.exists ) {
			this.isDirectory = Files.isDirectory(path, ( followSymbolicLinks ? null : LinkOption.NOFOLLOW_LINKS ) );
			this.isExecutable = Files.isExecutable(path);
			try {
				this.isHidden = Files.isHidden(path);
				this.sizeInBytes = Files.size(path);
				this.lastModifiedTime = Files.getLastModifiedTime(path, ( followSymbolicLinks ? null : LinkOption.NOFOLLOW_LINKS ));
				this.owner = Files.getOwner(path, ( followSymbolicLinks ? null : LinkOption.NOFOLLOW_LINKS ));
			} catch (IOException e) {
				exceptionMessage = e.getMessage();
			}
		}
	}

	@Override
	public String toString() {
		return "FileAttributes [path=" + path + ", exists=" + exists + ", isDirectory=" + isDirectory
				+ ", isExecutable=" + isExecutable + ", isHidden=" + isHidden + ", sizeInBytes=" + sizeInBytes
				+ ", lastModifiedTime=" + lastModifiedTime + ", owner=" + owner + ", exceptionMessage="
				+ exceptionMessage + "]";
	}


	
	
	
}
