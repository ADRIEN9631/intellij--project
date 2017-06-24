package org.easybooks.test.factory;

import javax.persistence.*;

/**
 * Created by Adrien on 2017/5/12.
 */
@Entity
@Table(name = "C", schema = "dbo", catalog = "student")
public class CEntity {
    private String cno;
    private String cname;
    private Integer credit;
    private String cdept;
    private String tname;
    private TEntity tByTno;

    @Id
    @Column(name = "CNO", nullable = false, length = 4)
    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "CNAME", nullable = true, length = 20)
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "CREDIT", nullable = true)
    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "CDEPT", nullable = true, length = 10)
    public String getCdept() {
        return cdept;
    }

    public void setCdept(String cdept) {
        this.cdept = cdept;
    }

    @Basic
    @Column(name = "TNAME", nullable = true, length = 8)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CEntity cEntity = (CEntity) o;

        if (cno != null ? !cno.equals(cEntity.cno) : cEntity.cno != null) return false;
        if (cname != null ? !cname.equals(cEntity.cname) : cEntity.cname != null) return false;
        if (credit != null ? !credit.equals(cEntity.credit) : cEntity.credit != null) return false;
        if (cdept != null ? !cdept.equals(cEntity.cdept) : cEntity.cdept != null) return false;
        if (tname != null ? !tname.equals(cEntity.tname) : cEntity.tname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cno != null ? cno.hashCode() : 0;
        result = 31 * result + (cname != null ? cname.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (cdept != null ? cdept.hashCode() : 0);
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TNO", referencedColumnName = "TNO")
    public TEntity gettByTno() {
        return tByTno;
    }

    public void settByTno(TEntity tByTno) {
        this.tByTno = tByTno;
    }
}
