package com.github.devcat24.mvc.entity.hr;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor     // @RequiredArgsConstructor, @NoArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode      // @EqualsAndHashCode(exclude = {"deptno"})
@ToString               //@ToString (exclude = {"deptno"})
@Entity(name="Emp01")
@Table(name="tb_emp_01")
public class Emp01 implements Serializable{

    @Setter @Getter @Id
    @Column(name="empno", unique=true, columnDefinition="Integer")
    public Integer empno;
    // Although JPA support column type like...  Integer(10), Float(7,2)
    // But, some databases (ex. H2, Postgresql...) have the issues about that patterns.
    // Just use like ...  Integer, Long, Float, String

    @Setter @Getter
    @Column(name="ename", nullable=false, columnDefinition="TEXT")
    public String ename;

    @Setter @Getter
    @Column(name="job", nullable=false, columnDefinition="TEXT")
    public String job;

    @Setter @Getter
    @Column(name="mgr", unique=false, columnDefinition="Integer")
    public Integer mgr;
}
