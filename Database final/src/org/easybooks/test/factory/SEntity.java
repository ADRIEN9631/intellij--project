package org.easybooks.test.factory;

import javax.persistence.*;

/**
 * Created by Adrien on 2017/5/12.
 */
@Entity
@Table(name = "S", schema = "dbo", catalog = "student")
public class SEntity {
    private String sno;
    private String sname;
    private String sex;
    private String age;
    private String sdept;
    private String logn;
    private String pswd;

    @Id
    @Column(name = "SNO", nullable = false, length = 4)
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    @Basic
    @Column(name = "SNAME", nullable = true, length = 8)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "SEX", nullable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "AGE", nullable = true, length = 2)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "SDEPT", nullable = true, length = 10)
    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    @Basic
    @Column(name = "LOGN", nullable = true, length = 50)
    public String getLogn() {
        return logn;
    }

    public void setLogn(String logn) {
        this.logn = logn;
    }

    @Basic
    @Column(name = "PSWD", nullable = true, length = 50)
    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SEntity sEntity = (SEntity) o;

        if (sno != null ? !sno.equals(sEntity.sno) : sEntity.sno != null) return false;
        if (sname != null ? !sname.equals(sEntity.sname) : sEntity.sname != null) return false;
        if (sex != null ? !sex.equals(sEntity.sex) : sEntity.sex != null) return false;
        if (age != null ? !age.equals(sEntity.age) : sEntity.age != null) return false;
        if (sdept != null ? !sdept.equals(sEntity.sdept) : sEntity.sdept != null) return false;
        if (logn != null ? !logn.equals(sEntity.logn) : sEntity.logn != null) return false;
        if (pswd != null ? !pswd.equals(sEntity.pswd) : sEntity.pswd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sno != null ? sno.hashCode() : 0;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sdept != null ? sdept.hashCode() : 0);
        result = 31 * result + (logn != null ? logn.hashCode() : 0);
        result = 31 * result + (pswd != null ? pswd.hashCode() : 0);
        return result;
    }
}
