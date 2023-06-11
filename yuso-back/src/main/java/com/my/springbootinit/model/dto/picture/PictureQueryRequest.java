package com.my.springbootinit.model.dto.picture;

import com.my.springbootinit.common.PageRequest;
import lombok.Data;

import java.io.Serializable;


@Data
public class PictureQueryRequest extends PageRequest implements Serializable {

    private String SearchText;

    private static final long serialVersionUID = 1L;
}
