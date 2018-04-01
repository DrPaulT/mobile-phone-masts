package uk.co.drdv.bink.mobilephonemasts.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "mast", indices = {@Index("current_rent")})
public class Mast implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "property_name")
    private String propertyName;
    @ColumnInfo(name = "property_address1")
    private String propertyAddress1;
    @ColumnInfo(name = "property_address2")
    private String propertyAddress2;
    @ColumnInfo(name = "property_address3")
    private String propertyAddress3;
    @ColumnInfo(name = "property_address4")
    private String propertyAddress4;
    @ColumnInfo(name = "unit_name")
    private String unitName;
    @ColumnInfo(name = "tenant_name")
    private String tenantName;
    @ColumnInfo(name = "lease_start_date")
    private String leaseStartDate;
    @ColumnInfo(name = "lease_end_date")
    private String leaseEndDate;
    @ColumnInfo(name = "lease_years")
    private Integer leaseYears;
    @ColumnInfo(name = "current_rent")
    private String currentRent;

    public Mast(String propertyName, String propertyAddress1, String propertyAddress2,
                String propertyAddress3, String propertyAddress4, String unitName, String tenantName,
                String leaseStartDate, String leaseEndDate, Integer leaseYears, String currentRent) {
        this.propertyName = propertyName;
        this.propertyAddress1 = propertyAddress1;
        this.propertyAddress2 = propertyAddress2;
        this.propertyAddress3 = propertyAddress3;
        this.propertyAddress4 = propertyAddress4;
        this.unitName = unitName;
        this.tenantName = tenantName;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.leaseYears = leaseYears;
        this.currentRent = currentRent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyAddress1() {
        return propertyAddress1;
    }

    public void setPropertyAddress1(String propertyAddress1) {
        this.propertyAddress1 = propertyAddress1;
    }

    public String getPropertyAddress2() {
        return propertyAddress2;
    }

    public void setPropertyAddress2(String propertyAddress2) {
        this.propertyAddress2 = propertyAddress2;
    }

    public String getPropertyAddress3() {
        return propertyAddress3;
    }

    public void setPropertyAddress3(String propertyAddress3) {
        this.propertyAddress3 = propertyAddress3;
    }

    public String getPropertyAddress4() {
        return propertyAddress4;
    }

    public void setPropertyAddress4(String propertyAddress4) {
        this.propertyAddress4 = propertyAddress4;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(String leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public String getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(String leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Integer getLeaseYears() {
        return leaseYears;
    }

    public void setLeaseYears(Integer leaseYears) {
        this.leaseYears = leaseYears;
    }

    public String getCurrentRent() {
        return currentRent;
    }

    public void setCurrentRent(String currentRent) {
        this.currentRent = currentRent;
    }
}
