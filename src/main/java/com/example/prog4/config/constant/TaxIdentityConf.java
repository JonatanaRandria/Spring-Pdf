package com.example.prog4.config.constant;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TaxIdentityConf {
    private String nif;
    private String stat;
    private String rcs;

}
