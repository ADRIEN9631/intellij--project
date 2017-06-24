package org.easybooks.test.factory;

import javax.persistence.*;

/**
 * Created by Adrien on 2017/5/12.
 */
@Entity
@Table(name = "SC", schema = "dbo", catalog = "student")
@IdClass(ScEntityPK.class)
public class ScEntity {
    private String sno;
    private String cno;
    private Integer grade;
    private Integer pgrade;
    private Integer egrade;
    private SEntity sBySno;
    private CEntity cByCno;

    @Id
    @Column(name = "SNO", nullable = true,insertable = false,updatable = false)
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }


    @Id
    @Column(name = "CNO", nullable = true,insertable = false,updatable = false)
    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "GRADE", nullable = true)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "PGRADE", nullable = true)
    public Integer getPgrade() {
        return pgrade;
    }

    public void setPgrade(Integer pgrade) {
        this.pgrade = pgrade;
    }

    @Basic
    @Column(name = "EGRADE", nullable = true)
    public Integer getEgrade() {
        return egrade;
    }

    public void setEgrade(Integer egrade) {
        this.egrade = egrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScEntity scEntity = (ScEntity) o;

        if (sno != null ? !sno.equals(scEntity.sno) : scEntity.sno != null) return false;
        if (cno != null ? !cno.equals(scEntity.cno) : scEntity.cno != null) return false;
        if (grade != null ? !grade.equals(scEntity.grade) : scEntity.grade != null) return false;
        if (pgrade != null ? !pgrade.equals(scEntity.pgrade) : scEntity.pgrade != null) return false;
        if (egrade != null ? !egrade.equals(scEntity.egrade) : scEntity.egrade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sno != null ? sno.hashCode() : 0;
        result = 31 * result + (cno != null ? cno.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (pgrade != null ? pgrade.hashCode() : 0);
        result = 31 * result + (egrade != null ? egrade.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "SNO", referencedColumnName = "SNO", nullable = true,insertable = false,updatable = false)
    public SEntity getsBySno() {
        return sBySno;
    }

    public void setsBySno(SEntity sBySno) {
        this.sBySno = sBySno;
    }

    @ManyToOne
    @JoinColumn(name = "CNO", referencedColumnName = "CNO", nullable = true,insertable = false,updatable = false)
    public CEntity getcByCno() {
        return cByCno;
    }

    public void setcByCno(CEntity cByCno) {
        this.cByCno = cByCno;
    }
}
