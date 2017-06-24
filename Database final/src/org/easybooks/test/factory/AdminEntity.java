package org.easybooks.test.factory;

import javax.persistence.*;

/**
 * Created by Adrien on 2017/5/12.
 */
@Entity
@Table(name = "Admin", schema = "dbo", catalog = "student")
public class AdminEntity {
    private String admincount;
    private String adminpswd;

    @Id
    @Column(name = "admincount", nullable = false, length = 50)
    public String getAdmincount() {
        return admincount;
    }

    public void setAdmincount(String admincount) {
        this.admincount = admincount;
    }

    @Basic
    @Column(name = "adminpswd", nullable = false, length = 50)
    public String getAdminpswd() {
        return adminpswd;
    }

    public void setAdminpswd(String adminpswd) {
        this.adminpswd = adminpswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (admincount != null ? !admincount.equals(that.admincount) : that.admincount != null) return false;
        if (adminpswd != null ? !adminpswd.equals(that.adminpswd) : that.adminpswd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = admincount != null ? admincount.hashCode() : 0;
        result = 31 * result + (adminpswd != null ? adminpswd.hashCode() : 0);
        return result;
    }
}
