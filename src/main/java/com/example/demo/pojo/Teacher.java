package com.example.demo.pojo;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/25 0025.
 */
@Data
@Entity
@Table(name = "Teachers")
public class Teacher implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tnos;
    @Column(name = "tnames")
    private String tnames;
    @Column(name = "typeses")
    private String typeses;
    @Column(name = "tpasswords")
    private String tpasswords;
    
}
