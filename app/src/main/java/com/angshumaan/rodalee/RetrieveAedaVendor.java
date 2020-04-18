package com.angshumaan.rodalee;

public class RetrieveAedaVendor {
    private String aedavendor_id,AEDAVendorName,Approvedby;

    public RetrieveAedaVendor(String aedavendor_id, String AEDAVendorName, String approvedby) {
        this.aedavendor_id = aedavendor_id;
        this.AEDAVendorName = AEDAVendorName;
        Approvedby = approvedby;
    }

    public String getAedavendor_id() {
        return aedavendor_id;
    }

    public void setAedavendor_id(String aedavendor_id) {
        this.aedavendor_id = aedavendor_id;
    }

    public String getAEDAVendorName() {
        return AEDAVendorName;
    }

    public void setAEDAVendorName(String AEDAVendorName) {
        this.AEDAVendorName = AEDAVendorName;
    }

    public String getApprovedby() {
        return Approvedby;
    }

    public void setApprovedby(String approvedby) {
        Approvedby = approvedby;
    }
}
