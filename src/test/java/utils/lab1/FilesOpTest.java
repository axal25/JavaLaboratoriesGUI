package utils.lab1;

import org.junit.jupiter.api.*;
import utils.lab1.FilesOp;

import java.io.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilesOpTest {
    public final static String existingFilePath = laboratories.lab1.Lab1_main.existingFilePath;
    public final static String existingFileName = laboratories.lab1.Lab1_main.existingFileName;
    public final static String existingFullFilePath = existingFilePath + "/" + existingFileName;
    public final static String notExistingFilePath = laboratories.lab1.Lab1_main.notExistingFilePath;
    public final static String notExistingFileName = laboratories.lab1.Lab1_main.notExistingFileName;
    public final static String notExistingFullFilePath = notExistingFilePath + "/" + notExistingFileName;
    public final static String notExistingCreatedJustForTestsFullFilePath = existingFilePath + "/" + "Test_openFileWriter_" + notExistingFileName;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void tryNewFile() {
        final String functionName = "@Test tryNewFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        Assertions.assertNotNull( FilesOp.tryNewFile( notExistingFullFilePath ) );
        Assertions.assertNotNull( FilesOp.tryNewFile( existingFullFilePath ) );
        Assertions.assertNull( FilesOp.tryNewFile( null ) );
        Assertions.assertNull( FilesOp.tryNewFile( "" ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(2)
    void openExistingFile() {
        final String functionName = "@Test openExistingFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertNotNull( FilesOp.openExistingFile( existingFilePath, existingFileName ) );
        Assertions.assertNull( FilesOp.openExistingFile( notExistingFilePath, notExistingFileName ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(3)
    void openExistingFile1() {
        final String functionName = "@Test openExistingFile1()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertNotNull( FilesOp.openExistingFile( existingFullFilePath ) );
        Assertions.assertNull( FilesOp.openExistingFile( notExistingFullFilePath ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(4)
    void openNewFile() {
        final String functionName = "@Test openNewFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertNotNull( FilesOp.openNewFile( existingFilePath, notExistingFileName ) );
        Assertions.assertNull( FilesOp.openNewFile( notExistingFilePath, notExistingFileName ) );
        Assertions.assertNull( FilesOp.openNewFile( existingFilePath, existingFileName ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(5)
    void openNewFile1() {
        final String functionName = "@Test openNewFile1()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertNotNull( FilesOp.openNewFile( existingFilePath + "/" + notExistingFileName) );
        Assertions.assertNull( FilesOp.openNewFile( existingFullFilePath ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(6)
    void openFileReader() {
        final String functionName = "@Test openFileReader()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        File notExistingFile = FilesOp.openNewFile( notExistingFullFilePath );

        Assertions.assertNotNull( FilesOp.openFileReader( existingFile ) );
        Assertions.assertNull( FilesOp.openFileReader( null ) );
        Assertions.assertNull( FilesOp.openFileReader( notExistingFile ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(7)
    void closeFileReader() {
        final String functionName = "@Test closeFileReader()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        FileReader fileReader = FilesOp.openFileReader( existingFile );

        Assertions.assertNotNull( FilesOp.closeFileReader( fileReader ) );
        Assertions.assertNull( FilesOp.closeFileReader( null ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(8)
    void openBufferedReader() {
        final String functionName = "@Test openBufferedReader()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        FileReader fileReader = FilesOp.openFileReader( existingFile );

        Assertions.assertNotNull( FilesOp.openBufferedReader( fileReader ) );
        Assertions.assertNull( FilesOp.openBufferedReader( null ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(9)
    void closeBufferedReader() {
        final String functionName = "@Test closeBufferedReader()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        FileReader fileReader = FilesOp.openFileReader( existingFile );
        BufferedReader bufferedReader = FilesOp.openBufferedReader( fileReader );

        Assertions.assertNotNull( FilesOp.closeBufferedReader( bufferedReader ) );
        Assertions.assertNull( FilesOp.closeBufferedReader( null ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(10)
    void getFileSizeBytes() {
        final String functionName = "@Test getFileSizeBytes()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        File notExistingFile = FilesOp.openNewFile( notExistingFullFilePath );

        Assertions.assertNotEquals( -1, FilesOp.getFileSizeBytes( existingFile ) );
        Assertions.assertEquals( -1, FilesOp.getFileSizeBytes( notExistingFile ) );
        Assertions.assertEquals( -1, FilesOp.getFileSizeBytes((File) null) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(11)
    void getFileSizeBytes1() {
        final String functionName = "@Test getFileSizeBytes1()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertNotEquals( -1, FilesOp.getFileSizeBytes( existingFullFilePath ) );
        Assertions.assertEquals( -1, FilesOp.getFileSizeBytes( notExistingFullFilePath ) );
        Assertions.assertEquals( -1, FilesOp.getFileSizeBytes("") );
        Assertions.assertEquals( -1, FilesOp.getFileSizeBytes((String) null) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(12)
    void sizeBytes2KiloB() {
        final String functionName = "@Test sizeBytes2KiloB()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertTrue( FilesOp.sizeBytes2KiloB( 1024 ) > 0 );
        Assertions.assertEquals( -1, FilesOp.sizeBytes2KiloB( -1 ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(13)
    void sizeBytes2MegaB() {
        final String functionName = "@Test sizeBytes2MegaB()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertTrue( FilesOp.sizeBytes2MegaB( 1024*1024 ) > 0 );
        Assertions.assertEquals( -1, FilesOp.sizeBytes2MegaB( -1 ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(14)
    void sizeBytes2GigaB() {
        final String functionName = "@Test sizeBytes2GigaB()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertTrue( FilesOp.sizeBytes2GigaB( 1024*1024*1024 ) > 0 );
        Assertions.assertEquals( -1, FilesOp.sizeBytes2GigaB( -1 ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(15)
    void getFilePath() {
        final String functionName = "@Test getFilePath()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertEquals( existingFilePath, FilesOp.getFilePath( existingFullFilePath ) );
        Assertions.assertEquals( existingFilePath, FilesOp.getFilePath( existingFilePath + "/" + notExistingFileName ) );
        Assertions.assertEquals( "", FilesOp.getFilePath(existingFileName) );
        Assertions.assertNull( FilesOp.getFilePath(notExistingFullFilePath) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(16)
    void isDirectory() {
        final String functionName = "@Test isDirectory()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertTrue( FilesOp.isDirectory( existingFilePath ) );
        Assertions.assertFalse( FilesOp.isDirectory( notExistingFilePath ) );
        Assertions.assertFalse( FilesOp.isDirectory( notExistingFullFilePath ) );

        utils.PrintSystem.outEnd(location);
    }

    FileWriter[] getOpenedFileWriters() {

        if( FilesOp.openExistingFile( notExistingCreatedJustForTestsFullFilePath ) != null ) {
            FilesOp.deleteFile( notExistingCreatedJustForTestsFullFilePath );
        }

        File existingFile = FilesOp.openExistingFile( existingFullFilePath );
        File notExistingFile = FilesOp.openNewFile( notExistingCreatedJustForTestsFullFilePath );

        FileWriter[] fileWritersList = new FileWriter[3];
        fileWritersList[0] = FilesOp.openFileWriter( existingFile, true );
        fileWritersList[1] = FilesOp.openFileWriter( notExistingFile, true );
        fileWritersList[2] = FilesOp.openFileWriter( null, true );

        return fileWritersList;
    }

    @Test
    @Order(17)
    void openFileWriter() {
        final String functionName = "@Test openFileWriter()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        FileWriter[] fileWritersList = getOpenedFileWriters();

        Assertions.assertNotNull( fileWritersList[0] );
        Assertions.assertNotNull( fileWritersList[1] );
        Assertions.assertNull( fileWritersList[2] );

        utils.PrintSystem.outEnd(location);
    }

    BufferedWriter[] getOpenedBufferedWriters() {

        FileWriter[] fileWriterList = getOpenedFileWriters();
        BufferedWriter[] bufferedWriterList = new BufferedWriter[3];

        bufferedWriterList[0] = FilesOp.openBufferedWriter( fileWriterList[0] );
        bufferedWriterList[1] = FilesOp.openBufferedWriter( fileWriterList[1] );
        bufferedWriterList[2] = FilesOp.openBufferedWriter( fileWriterList[2] );

        return bufferedWriterList;
    }

    @Test
    @Order(18)
    void openBufferedWriter() {
        final String functionName = "@Test openBufferedWriter()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        BufferedWriter[] bufferedWriterList = getOpenedBufferedWriters();

        Assertions.assertNotNull( bufferedWriterList[0] );
        Assertions.assertNotNull( bufferedWriterList[1] );
        Assertions.assertNull( bufferedWriterList[2] );

        utils.PrintSystem.outEnd(location);
    }

    BufferedWriter[] getClosedBufferedWriters() {
        BufferedWriter[] bufferedWriterList = getOpenedBufferedWriters();

        bufferedWriterList[0] = FilesOp.closeBufferedWriter( bufferedWriterList[0] );
        bufferedWriterList[1] = FilesOp.closeBufferedWriter( bufferedWriterList[1] );
        bufferedWriterList[2] = FilesOp.closeBufferedWriter( bufferedWriterList[2] );

        return bufferedWriterList;
    }

    @Test
    @Order(19)
    void closeBufferedWriter() {
        final String functionName = "@Test closeBufferedWriter()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        BufferedWriter[] bufferedWriterList = getClosedBufferedWriters();

        Assertions.assertNotNull( bufferedWriterList[0] );
        Assertions.assertNotNull( bufferedWriterList[1] );
        Assertions.assertNull( bufferedWriterList[2] );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(20)
    void closeFileWriter() {
        final String functionName = "@Test closeFileWriter()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        FileWriter[] fileWriterList = getOpenedFileWriters();

        fileWriterList[0] = FilesOp.closeFileWriter( fileWriterList[0] );
        fileWriterList[1] = FilesOp.closeFileWriter( fileWriterList[1] );
        fileWriterList[2] = FilesOp.closeFileWriter( fileWriterList[2] );

        Assertions.assertNotNull( FilesOp.closeFileWriter( fileWriterList[0] ) );
        Assertions.assertNotNull( FilesOp.closeFileWriter( fileWriterList[1] ) );
        Assertions.assertNull( FilesOp.closeFileWriter( fileWriterList[2] ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(21)
    void deleteFile() {
        final String functionName = "@Test deleteFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertTrue( FilesOp.deleteFile( notExistingCreatedJustForTestsFullFilePath ) );
        Assertions.assertFalse( FilesOp.deleteFile( notExistingCreatedJustForTestsFullFilePath ) );
        Assertions.assertFalse( FilesOp.deleteFile( notExistingFullFilePath ) );
        Assertions.assertFalse( FilesOp.deleteFile( "" ) );
        Assertions.assertFalse( FilesOp.deleteFile( null ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(22)
    void getStringSizeBytes() {
        final String functionName = "@Test deleteFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        Assertions.assertEquals( 13, FilesOp.getStringSizeBytes( "Bla4bla8bla13" ));
        Assertions.assertEquals( 13, FilesOp.getStringSizeBytes( new String("Bla4bla8bla13") ) );
        Assertions.assertEquals( -1, FilesOp.getStringSizeBytes( null ));
        Assertions.assertEquals( 0, FilesOp.getStringSizeBytes( "" ));

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(23)
    void renameFile() {
        final String functionName = "@Test deleteFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        String newFullFilePath = existingFilePath + "/" + "FilesOpTest_renameFile.txt";
        String renamedFullFilePath = existingFilePath + "/" + "renamed_FilesOpTest_renameFile.txt";
        File newFile = FilesOp.createFile( newFullFilePath );
        Assertions.assertNotNull( newFile );
        File renamedFile = FilesOp.openNewFile( renamedFullFilePath );
        Assertions.assertNotNull( renamedFile );
        File returnedRenamedFile = FilesOp.renameFile( newFile, renamedFile );
        Assertions.assertNotNull( returnedRenamedFile );
        System.err.println( "returnedRenamedFile.getAbsolutePath() = " + returnedRenamedFile.getAbsolutePath() );
        boolean didDelete = FilesOp.deleteFile( renamedFullFilePath );
        Assertions.assertTrue( didDelete );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(24)
    void renameFile1() {
        final String functionName = "@Test deleteFile()";
        final String location = FilesOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin(location);

        String newFullFilePath = existingFilePath + "/" + "FilesOpTest_renameFile1.txt";
        String renamedFullFilePath = existingFilePath + "/" + "renamed_FilesOpTest_renameFile1.txt";
        File file = FilesOp.createFile( newFullFilePath );
        Assertions.assertNotNull( file );
        String returnedRenamedFullFilePath = FilesOp.renameFile( newFullFilePath, renamedFullFilePath );
        Assertions.assertNotNull( returnedRenamedFullFilePath );
        System.err.println( "returnedRenamedFullFilePath = " + returnedRenamedFullFilePath );
        boolean didDelete = FilesOp.deleteFile( renamedFullFilePath );
        Assertions.assertTrue( didDelete );

        utils.PrintSystem.outEnd(location);
    }
}