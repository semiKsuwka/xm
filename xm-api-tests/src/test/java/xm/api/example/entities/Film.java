package xm.api.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Film {
    private String name;
    private int planetCount;
}
