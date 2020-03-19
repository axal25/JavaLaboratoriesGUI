package laboratories.lab1;

import laboratories.lab1.exceptions.CopyFileException;
import utils.ExceptionMessageGenerator;
import utils.lab1.FilesOp;

import java.io.*;

public class CrimesFileOp {
    public static final double delta = 25/100;

    /** returns File new created file & copied file **/
    public static File cutFileToSize( String fileName, String newFileName, double sizeLimitInKiloB, boolean doAppend, boolean doDelete, boolean doRename ) {
        final String functionName = "cutFileToSize( String fileName, String newFileName, int sizeInKiloB, boolean doDelete )";
        File file = FilesOp.openExistingFile( fileName );
        double fileSizeInBytes = FilesOp.getFileSizeBytes( file );
        double fileSizeInKiloB = FilesOp.sizeBytes2KiloB( fileSizeInBytes );
        if( sizeLimitInKiloB*(1 + delta) < fileSizeInKiloB ) {
            try {
                File newCopiedFile = copyFile(file, newFileName, sizeLimitInKiloB, doAppend, doDelete, doRename);
                return newCopiedFile;
            } catch( CopyFileException e ) {
                String exceptionMessage = ExceptionMessageGenerator.getMessage(CrimesFileOp.class.getName(), functionName, e);
                System.out.println(exceptionMessage);
                return null;
            }
        }
        else {
            return file;
        }
    }

    private static File copyFile(File file, String newFileName, double sizeLimitInKiloB, boolean doAppend, boolean doDelete, boolean doRename) throws CopyFileException {
        final String functionName = "copyFile(File file, String newFileName)";
        if( newFileName.compareTo( file.getAbsolutePath() ) == 0 ) {
            throw new CopyFileException(
                    CrimesFileOp.class.getName(),
                    functionName,
                    "function parameters File[.getPath()] and newFileName are the same: " + "\n\r" +
                            "\"" + file.getPath() + "\"" + "\n\r" +
                            "&" + "\n\r" +
                            "\"" + newFileName + "\""
            );
        }
        else {
            File newCopiedFile = FilesOp.openNewFile( newFileName );
            newCopiedFile = doCopyFile( file, newCopiedFile, sizeLimitInKiloB, doAppend);
            newCopiedFile = doRenameAndOrDelete( file, newCopiedFile, doDelete, doRename);

            return newCopiedFile;
        }
    }

    private static File doCopyFile( File file, File newCopiedFile, double sizeLimitInKiloB, boolean doAppend) {
        final String functionName = "doCopyFile( File file, File newCopiedFile, double sizeLimitInKiloB, boolean doAppend)";
        FileReader originalFileReader = FilesOp.openFileReader( file );
        FileWriter newFileWriter = FilesOp.openFileWriter(newCopiedFile, doAppend);
        BufferedReader originalBufferedReader = FilesOp.openBufferedReader( originalFileReader );
        BufferedWriter newBufferedWriter = FilesOp.openBufferedWriter( newFileWriter );
        String curLine = null;
        double currentFileSizeKiloB = 0;
        double currentLineSizeKiloB = 0;
        double appendedFileSizeKiloB = 0;
        try {
            curLine = originalBufferedReader.readLine();
            currentFileSizeKiloB = FilesOp.sizeBytes2KiloB(FilesOp.getFileSizeBytes( newCopiedFile.getAbsoluteFile() ));
            currentLineSizeKiloB = FilesOp.sizeBytes2KiloB(FilesOp.getStringSizeBytes( curLine ));
            appendedFileSizeKiloB = currentFileSizeKiloB + currentLineSizeKiloB;

            while( curLine != null &&  sizeLimitInKiloB > appendedFileSizeKiloB ) {
                newBufferedWriter.write( curLine );
                newBufferedWriter.newLine();
                curLine = originalBufferedReader.readLine();
                currentFileSizeKiloB = appendedFileSizeKiloB;
                currentLineSizeKiloB = FilesOp.sizeBytes2KiloB(FilesOp.getStringSizeBytes( curLine ));
                appendedFileSizeKiloB = currentFileSizeKiloB + currentLineSizeKiloB;
            }
        } catch ( IOException e) {
            String exceptionMessage = ExceptionMessageGenerator.getMessage(CrimesFileOp.class.getName(), functionName, e);
            System.out.println(exceptionMessage);
            return null;
        }
        finally {
            originalBufferedReader = FilesOp.closeBufferedReader( originalBufferedReader );
            newBufferedWriter = FilesOp.closeBufferedWriter( newBufferedWriter );
            originalFileReader = FilesOp.closeFileReader( originalFileReader );
            newFileWriter = FilesOp.closeFileWriter( newFileWriter );
        }
        return newCopiedFile;
    }

    private static File doRenameAndOrDelete( File file, File newCopiedFile, boolean doDelete, boolean doRename ) {
        if( doRename ) {
            FilesOp.deleteFile( file.getAbsolutePath() );
            newCopiedFile = FilesOp.renameFile( newCopiedFile, file );
        }
        else {
            if (doDelete) {
                FilesOp.deleteFile( file.getAbsolutePath() );
            }
        }
        return newCopiedFile;
    }
}
