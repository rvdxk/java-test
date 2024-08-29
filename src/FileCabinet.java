import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class FileCabinet implements Cabinet {

    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    // zwraca dowolny element o podanej nazwie
    @Override
    public Optional<Folder> findFolderByName(String name) {

        return folders.stream()
                .filter(object -> object.getName().equals(name))
                .findFirst();
    }

    // zwraca wszystkie foldery podanego rozmiaru SMALL/MEDIUM/LARGE
    @Override
    public List<Folder> findFoldersBySize(String size) {

        List<Folder> result = new ArrayList<>();
        Stack<Folder> stack = new Stack<>();

        stack.addAll(folders);

        while (!stack.isEmpty()){
            Folder current = stack.pop();

            if (current.getSize().equals(size)) {
                result.add(current);
            }

            if (current instanceof MultiFolder) {
                stack.addAll(((MultiFolder) current).getFolders());
            }
        }
        return result;
    }

    //zwraca liczbę wszystkich obiektów tworzących strukturę
    @Override
    public int count() {

        int count = 0;
        Stack<Folder> stack = new Stack<>();

        stack.addAll(folders);

        while (!stack.isEmpty()) {
            Folder current = stack.pop();
            count++;

            if (current instanceof MultiFolder){
                stack.addAll(((MultiFolder)current).getFolders());
            }
        }

        return count;
    }
}
