package ro.utcn.sd.cata.stackoverflow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.cata.stackoverflow.entity.Question;
import ro.utcn.sd.cata.stackoverflow.entity.Tag;
import ro.utcn.sd.cata.stackoverflow.repository.RepositoryFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public void addTag(String name, Question question){
       Optional<Tag> tag = repositoryFactory.createTagRepository().findByName(name);
       if(tag.isPresent()) {
           repositoryFactory.createTagRepository().addTag(tag.get(), question);
       }
       else{
           repositoryFactory.createTagRepository().addTag(new Tag(name), question);
       }
    }

    @Transactional
    public Optional<Tag> findByName(String name){
        return repositoryFactory.createTagRepository().findByName(name);
    }

}
