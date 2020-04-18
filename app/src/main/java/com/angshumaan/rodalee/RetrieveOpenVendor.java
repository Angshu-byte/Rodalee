package com.angshumaan.rodalee;

public class RetrieveOpenVendor {
    private String OpenVendor_id,OpenVendorName,Approvedby;

    public RetrieveOpenVendor(String openVendor_id, String openVendorName, String approvedby) {
        OpenVendor_id = openVendor_id;
        OpenVendorName = openVendorName;
        Approvedby = approvedby;
    }

    public String getOpenVendor_id() {
        return OpenVendor_id;
    }

    public void setOpenVendor_id(String openVendor_id) {
        OpenVendor_id = openVendor_id;
    }

    public String getOpenVendorName() {
        return OpenVendorName;
    }

    public void setOpenVendorName(String openVendorName) {
        OpenVendorName = openVendorName;
    }

    public String getApprovedby() {
        return Approvedby;
    }

    public void setApprovedby(String approvedby) {
        Approvedby = approvedby;
    }
}