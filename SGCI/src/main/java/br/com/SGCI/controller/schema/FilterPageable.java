package br.com.SGCI.controller.schema;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;

public class FilterPageable {

    @NotNull
    public Integer page;

    @NotNull
    public Integer size;

    @NotNull
    public Sort.Direction direction;

    @NotNull
    public String ordenarPor;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public String getOrdenarPor() {
        return ordenarPor;
    }

    public void setOrdenarPor(String ordenarPor) {
        this.ordenarPor = ordenarPor;
    }
}
