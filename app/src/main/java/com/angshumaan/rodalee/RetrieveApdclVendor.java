package com.angshumaan.rodalee;

public class RetrieveApdclVendor {
    private String apdclvendor_id,APDCLVendorName,Approvedby;

    public RetrieveApdclVendor(String apdclvendor_id, String APDCLVendorName, String approvedby) {
        this.apdclvendor_id = apdclvendor_id;
        this.APDCLVendorName = APDCLVendorName;
        Approvedby = approvedby;
    }

    public String getApdclvendor_id() {
        return apdclvendor_id;
    }

    public void setApdclvendor_id(String apdclvendor_id) {
        this.apdclvendor_id = apdclvendor_id;
    }

    public String getAPDCLVendorName() {
        return APDCLVendorName;
    }

    public void setAPDCLVendorName(String APDCLVendorName) {
        this.APDCLVendorName = APDCLVendorName;
    }

    public String getApprovedby() {
        return Approvedby;
    }

    public void setApprovedby(String approvedby) {
        Approvedby = approvedby;
    }
}
