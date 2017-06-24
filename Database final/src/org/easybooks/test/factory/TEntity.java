package org.easybooks.test.factory;

import javax.persistence.*;

/**
 * Created by Adrien on 2017/5/12.
 */
@Entity
@Table(name = "T", schema = "dbo", catalog = "student")
public class TEntity {
    private String tno;
    private String tname;
    private String logn;
    private String pswd;
    private boolean xb;
    private String zc;
    private String dept;

    @Id
    @Column(name = "TNO", nullable = false, length = 50)
    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    @Basic
    @Column(name = "TNAME", nullable = false, length = 10)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Basic
    @Column(name = "LOGN", nullable = false, length = 50)
    public String getLogn() {
        return logn;
    }

    public void setLogn(String logn) {
        this.logn = logn;
    }

    @Basic
    @Column(name = "PSWD", nullable = false, length = 50)
    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Basic
    @Column(name = "XB", nullable = false)
    public boolean isXb() {
        return xb;
    }

    public void setXb(boolean xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "ZC", nullable = false, length = 10)
    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEntity tEntity = (TEntity) o;

        if (xb != tEntity.xb) return false;
        if (tno != null ? !tno.equals(tEntity.tno) : tEntity.tno != null) return false;
        if (tname != null ? !tname.equals(tEntity.tname) : tEntity.tname != null) return false;
        if (logn != null ? !logn.equals(tEntity.logn) : tEntity.logn != null) return false;
        if (pswd != null ? !pswd.equals(tEntity.pswd) : tEntity.pswd != null) return false;
        if (zc != null ? !zc.equals(tEntity.zc) : tEntity.zc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tno != null ? tno.hashCode() : 0;
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (logn != null ? logn.hashCode() : 0);
        result = 31 * result + (pswd != null ? pswd.hashCode() : 0);
        result = 31 * result + (xb ? 1 : 0);
        result = 31 * result + (zc != null ? zc.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "DEPT", nullable = true, length = 50)
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
