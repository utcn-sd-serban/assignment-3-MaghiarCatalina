package ro.utcn.sd.cata.stackoverflow.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Tag {
    private Integer id;
    private String name;

    public Tag(String name){
        this.name = name;
    }
    public Tag(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
