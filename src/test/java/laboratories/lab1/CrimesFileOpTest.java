package laboratories.lab1;

import org.junit.jupiter.api.*;
import utils.lab1.FilesOp;

import java.io.File;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrimesFileOpTest {
    public final static String existingFilePath = laboratories.lab1.Lab1_main.existingFilePath;
    public final static String existingFileName = laboratories.lab1.Lab1_main.existingFileName;
    public final static String existingFullFilePath = existingFilePath + "/" + existingFileName;
    public final static String notExistingFilePath = laboratories.lab1.Lab1_main.notExistingFilePath;
    public final static String notExistingFileName = laboratories.lab1.Lab1_main.notExistingFileName;
    public final static String notExistingFullFilePath = notExistingFilePath + "/" + notExistingFileName;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(25)
    void cutFileToSize1() {
        final String functionName = "@Test cutFileToSize1()";
        final String location = CrimesFileOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        String newFilePath = existingFilePath + "/" + "CrimesFileOpTest_cutFileToSize_1" + existingFileName;

        if( FilesOp.openNewFile( newFilePath ) == null ) {
            Assertions.assertTrue( FilesOp.deleteFile( newFilePath ) );
        }
        File newFile = FilesOp.openNewFile( newFilePath );
        File originalFile = FilesOp.openExistingFile( existingFullFilePath );

        double sizeLimitInKiloB = 100.0;
        File returnedFile = CrimesFileOp.cutFileToSize( existingFullFilePath, newFilePath, sizeLimitInKiloB, false, false, false );
        Assertions.assertNotNull( returnedFile );
        Assertions.assertEquals( newFile, returnedFile );
        double returnedFileSizeKiloB = Math.floor(FilesOp.sizeBytes2KiloB(FilesOp.getFileSizeBytes( returnedFile )));
        org.hamcrest.MatcherAssert.assertThat(
                sizeLimitInKiloB,
                org.hamcrest.Matchers.greaterThanOrEqualTo( returnedFileSizeKiloB )
        );
        Assertions.assertTrue( returnedFileSizeKiloB <= sizeLimitInKiloB );
        Assertions.assertTrue( FilesOp.deleteFile(newFilePath) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(26)
    void cutFileToSize2() {
        final String functionName = "@Test cutFileToSize2()";
        final String location = CrimesFileOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        double sizeLimitInKiloB = 2*1024*1024;
        String newFilePath = existingFilePath + "/" + "CrimesFileOpTest_cutFileToSize2_" + existingFileName;
        File originalFile = FilesOp.openExistingFile( existingFullFilePath );
        File returnedFile = CrimesFileOp.cutFileToSize( existingFullFilePath, newFilePath, sizeLimitInKiloB, false, false, false );
        Assertions.assertNotNull( returnedFile );
        Assertions.assertEquals( originalFile, returnedFile );
        double returnedFileSizeKiloB = FilesOp.sizeBytes2KiloB(FilesOp.getFileSizeBytes( returnedFile ));
        org.hamcrest.MatcherAssert.assertThat(
                sizeLimitInKiloB,
                org.hamcrest.Matchers.greaterThan( returnedFileSizeKiloB )
        );
        Assertions.assertTrue( returnedFileSizeKiloB < sizeLimitInKiloB );
        Assertions.assertFalse( FilesOp.deleteFile( newFilePath ) );

        utils.PrintSystem.outEnd(location);
    }

    @Test
    @Order(27)
    void cutFileToSize3() {
        final String functionName = "@Test cutFileToSize3()";
        final String location = CrimesFileOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        double sizeLimitInKiloB = 100.0;
        Assertions.assertNull( CrimesFileOp.cutFileToSize( existingFullFilePath, existingFullFilePath, sizeLimitInKiloB, false, false, false ) );

        utils.PrintSystem.outEnd(location);
    }


    @Test
    @Order(28)
    void cutFileToSize4() {
        final String functionName = "@Test cutFileToSize4()";
        final String location = CrimesFileOpTest.class.getName() + " >>> " + functionName;
        utils.PrintSystem.outBegin( location );

        String copiedFullFilePath = existingFilePath + "/" + "CrimesFileOpTest_cutFileToSize4_Rename";
        double sizeLimitInKiloB = 0.5*1024;
        File copiedFile = CrimesFileOp.cutFileToSize( existingFullFilePath, copiedFullFilePath, sizeLimitInKiloB, false, false, false );
        String newFilePath = existingFilePath + "/" + "renamed_CrimesFileOpTest_cutFileToSize4_Rename";
        sizeLimitInKiloB = 1024;
        File returnedFile = CrimesFileOp.cutFileToSize( copiedFullFilePath, newFilePath, sizeLimitInKiloB, false, true, true );
        Assertions.assertNotNull( returnedFile );
        Assertions.assertEquals( copiedFile.getAbsolutePath(), returnedFile.getAbsolutePath() );
        double returnedFileSizeKiloB = FilesOp.sizeBytes2KiloB(FilesOp.getFileSizeBytes( returnedFile ));
        org.hamcrest.MatcherAssert.assertThat(
                sizeLimitInKiloB,
                org.hamcrest.Matchers.greaterThan( returnedFileSizeKiloB )
        );
        Assertions.assertTrue( returnedFileSizeKiloB < sizeLimitInKiloB );
        Assertions.assertTrue( FilesOp.deleteFile( copiedFullFilePath ) );
        Assertions.assertFalse( FilesOp.deleteFile( newFilePath ) );

        utils.PrintSystem.outEnd(location);
    }
}