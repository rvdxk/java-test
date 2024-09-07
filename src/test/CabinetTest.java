import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CabinetTest {

    private FileCabinet cabinet;
    private Folder smallFolder, mediumFolder, largeFolder;
    private MultiFolder multiFolder;

    @BeforeEach
    public void setUp() {

        // tworzenie folderów
        smallFolder = new Folder() {
            @Override
            public String getName() {
                return "smallFolder";
            }

            @Override
            public String getSize() {
                return "SMALL";
            }
        };

        mediumFolder = new Folder() {
            @Override
            public String getName() {
                return "mediumFolder";
            }

            @Override
            public String getSize() {
                return "MEDIUM";
            }
        };

        largeFolder = new Folder() {
            @Override
            public String getName() {
                return "largeFolder";
            }

            @Override
            public String getSize() {
                return "LARGE";
            }
        };

        multiFolder = new MultiFolder() {
            @Override
            public String getName() {
                return "multiFolder";
            }

            @Override
            public String getSize() {
                return "MEDIUM";
            }

            @Override
            public List<Folder> getFolders() {
                List<Folder> folders = new ArrayList<>();
                folders.add(smallFolder);
                folders.add(mediumFolder);
                folders.add(largeFolder);
                return folders;
            }
        };

        // tworzenie FileCabinet
        List<Folder> folders = new ArrayList<>();
        folders.add(smallFolder);
        folders.add(multiFolder);
        folders.add(mediumFolder);
        cabinet = new FileCabinet(folders);
    }

    @Test
    void findFolderByName_ShouldPassIfNameIsPresentAndEquals() {
        Optional<Folder> folderByName = cabinet.findFolderByName("smallFolder");
        assertTrue(folderByName.isPresent());
        assertEquals("smallFolder", folderByName.get().getName());
    }

    @Test
    void findFoldersBySize_ShouldPassIfFindFoldersBySize() {
        List<Folder> folderBySize = cabinet.findFoldersBySize("MEDIUM");
        for (Folder folder : folderBySize){
            assertEquals("MEDIUM", folder.getSize());
        }
    }

    @Test
    void count_ShouldPassIfFolderListCountIsCorrect() {
     List<Folder> folderList = new ArrayList<>();
     folderList.add(smallFolder);
     folderList.add(mediumFolder);
     folderList.add(largeFolder);
     folderList.add(multiFolder);

     FileCabinet fileCabinet = new FileCabinet(folderList);

     assertEquals(7,fileCabinet.count());
    }
}