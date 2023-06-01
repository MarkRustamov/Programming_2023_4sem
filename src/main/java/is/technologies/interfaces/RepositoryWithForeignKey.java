package is.technologies.interfaces;

import java.util.List;

public interface RepositoryWithForeignKey<T> {
    List<T> getAllByVId(long id);
}
