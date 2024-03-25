 package dbTest;

/**
 *
 * @author dakinkuolie
 */
public class GenericSqlStatement {
    public static String insuranceQuery = 
"SELECT J.branch_no, J.rim_no,J.title_1,J.acct_no,J.Insurance_Type,J.Premium_Type,J.Tenor, J.effective_dt,\n" +
"\n" +
"--J.Offer_Date,\n" +
"\n" +
"CASE WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (420 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type  = 'Single Cover -Monthly'  then (660 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly' then (400 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly' then (630 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (320 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (590 * J.Tenor)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (420 * 6)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type  = 'Single Cover -Monthly'  then (660 * 6)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly' then (400 * 6)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly' then (630 * 6)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (320 * 6)\n" +
"\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (590 * 6)\n" +
"\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type  = 'Single Cover- Annual' ) then 4800\n" +
"\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type  = 'Single Cover- Annual' ) then 7800\n" +
"\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type = 'Multiple Cover-Annual') then 4600\n" +
"\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type = 'Multiple Cover-Annual') then  7200\n" +
"\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type = 'Child Cover-Annual')  then 3700\n" +
"\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type = 'Child Cover-Annual')  then 6900\n" +
"\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type = 'Category A')then 2160\n" +
"\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type = 'Category B')then 4320\n" +
"\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type= 'Category C')then 7200\n" +
"\n" +
"     END 'Premium_Generated',\n" +
"\n" +
"     J.amt'Premium_Paid'\n" +
"\n" +
"FROM\n" +
" \n" +
"(SELECT distinct rm.branch_no,dp.rim_no,dp.title_1,hist.acct_no,fld.field_label 'Insurance_Type',gb.[value] 'Premium_Type', hist.effective_dt,\n" +
"\n" +
"--gb.create_dt'Offer_Date',\n" +
" \n" +
"hist.amt,\n" +
"\n" +
"hist.description,\n" +
"\n" +
"/*(select ln.trm from ln_display ln where ln.rim_no = dp.rim_no and ln.status = 'active' and ln.class_code <> 701\n" +
"\n" +
"and ln.create_dt = (select max(ln_disp2.create_dt) from ln_display ln_disp2 where ln_disp2.status = 'active' and ln_disp2.class_code <> 701\n" +
"\n" +
"and ln_disp2.acct_no = ln.acct_no))'Tenor',*/\n" +
"\n" +
"ln.trm'Tenor',\n" +
"\n" +
"gb.field_id\n" +
"\n" +
"FROM dp_acct dp \n" +
"\n" +
"JOIN rm_acct rm ON dp.rim_no = rm.rim_no\n" +
"\n" +
"join dp_history hist on dp.acct_no = hist.acct_no\n" +
"\n" +
"JOIN gb_user_defined gb ON cast(gb.acct_no_key as int) = dp.rim_no\n" +
"\n" +
"join ad_gb_user_fld fld on fld.field_id = gb.field_id\n" +
"\n" +
"join ad_gb_user_fld_lst fld_lst on fld_lst.field_id = fld.field_id\n" +
"\n" +
"left outer join ln_display ln on dp.rim_no = ln.rim_no and ln.status = 'active' and ln.class_code <> 701\n" +
"\n" +
"                              and ln.create_dt = (select max(ln_disp2.create_dt) from ln_display ln_disp2 \n" +
"\n" +
"                              where ln_disp2.status = 'active' \n" +
"\n" +
"                              and ln_disp2.class_code <> 701\n" +
"\n" +
"                              and ln_disp2.acct_no = ln.acct_no)\n" +
"\n" +
"where dp.class_code <> 191\n" +
"\n" +
"AND dp.acct_type <> 'CT'\n" +
"\n" +
"and hist.tran_code = 152\n" +
"\n" +
"and dp.status in ('active','restricted')\n" +
"\n" +
"AND gb.field_id in (52,51) and gb.value <> 'None'\n" +
"\n" +
"--and rm.rim_no not in (128757,116958,126839,14157,1109344)\n" +
"\n" +
"/*and rm.rim_no not in (121387,120947, 94242,131053,92433,106990,51224,133884,122975,123527,\n" +
"\n" +
"                        122849,15064,121024,113075,122301,3067,61546,119278,128356,109448,\n" +
"\n" +
"                        127167,117288,117907,114969,92776,127136,127405,106123,124951,112008,104969,136290)*/\n" +
"\n" +
"and fld_lst.list_id in (168,169,170,171,172,173,174,175,176,177,178,179)\n" +
"\n" +
"and hist.description like '%Insurance %')J\n" +
"\n" +
"where J.effective_dt between '3/1/2024' and '3/8/2024'\n" +
"\n" +
"--<:and: J.branch_no:2>";
    
    
    public static String insuranceSubQuery = "SELECT J.branch_no,J.title_1,J.acct_no,J.Insurance_Type,J.Premium_Type,J.Tenor,J.effective_dt,\n" +
"--J.Offer_Date,\n" +
"CASE WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (420 * J.Tenor)\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type  = 'Single Cover -Monthly'  then (660 * J.Tenor)\n" +
"     WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly' then (400 * J.Tenor)\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly' then (630 * J.Tenor)\n" +
"     WHEN J.Tenor <> null and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (320 * J.Tenor)\n" +
"     WHEN J.Tenor <> null and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (590 * J.Tenor)\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (420 * 6)\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type  = 'Single Cover -Monthly'  then (660 * 6)\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly' then (400 * 6)\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly' then (630 * 6)\n" +
"     WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (320 * 6)\n" +
"     WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (590 * 6)\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type  = 'Single Cover- Annual' ) then 4800\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type  = 'Single Cover- Annual' ) then 7800\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type = 'Multiple Cover-Annual') then 4600\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type = 'Multiple Cover-Annual') then  7200\n" +
"     WHEN (J.field_id = 52 and J.Premium_Type = 'Child Cover-Annual')  then 3700\n" +
"     WHEN (J.field_id = 51 and J.Premium_Type = 'Child Cover-Annual')  then 6900\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type = 'Category A')then 2160\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type = 'Category B')then 4320\n" +
"     --WHEN (J.field_id = 53 and J.Premium_Type= 'Category C')then 7200\n" +
"     END 'Premium_Generated',\n" +
"     J.amt'Premium_Paid',\n" +
"     /*Malaria*/\n" +
"     CASE WHEN J.Tenor = 6 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (2000)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (2450)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (2800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (3150)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (3500)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (3850)\n" +
"          WHEN (J.field_id = 52 and J.Premium_Type  = 'Single Cover- Annual' ) then 4000\n" +
"          WHEN J.Tenor = 6 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (2000)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (2450)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (2800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (3150)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (3500)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (3850)\n" +
"          WHEN (J.field_id = 52 and J.Premium_Type  = 'Multiple Cover-Annual' ) then 4000\n" +
"          WHEN J.Tenor = 6 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (1800)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (2450)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (2800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (3150)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (3500)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (3850)\n" +
"          WHEN (J.field_id = 52 and J.Premium_Type  = 'Child Cover-Annual' ) then 3500\n" +
"          WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Single Cover -Monthly'  then (333.34 * 6)\n" +
"          WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Multiple Cover -Monthly'  then (333.34 * 6)\n" +
"          WHEN J.Tenor IS null and J.field_id = 52 and J.Premium_Type = 'Child Cover-Monthly'  then (333.34 * 6)\n" +
"          --WHEN (J.field_id = 52 and J.Premium_Type  = 'Single Cover- Annual' ) then (291.64*12)\n" +
"          --WHEN (J.field_id = 52 and J.Premium_Type  = 'Multiple Cover-Annual' ) then (291.64*12)\n" +
"          --WHEN (J.field_id = 52 and J.Premium_Type  = 'Child Cover-Annual' ) then (291.64*12)\n" +
"     /*Micro*/\n" +
"          WHEN J.Tenor = 6 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (3600)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (4200)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (4800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (5400)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (6000)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (6600)\n" +
"          WHEN (J.field_id = 51 and J.Premium_Type  = 'Single Cover- Annual' ) then 5500\n" +
"          WHEN J.Tenor = 6 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (3600)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (4200)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (4800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (5400)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (6000)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (6600)\n" +
"          WHEN (J.field_id = 51 and J.Premium_Type  = 'Multiple Cover-Annual' ) then 5500\n" +
"          WHEN J.Tenor = 6 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (3300)\n" +
"          WHEN J.Tenor = 7 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (4200)\n" +
"          WHEN J.Tenor = 8 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (4800)\n" +
"          WHEN J.Tenor = 9 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (5400)\n" +
"          WHEN J.Tenor = 10 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (6000)\n" +
"          WHEN J.Tenor = 11 and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (6600)\n" +
"          WHEN (J.field_id = 51 and J.Premium_Type  = 'Child Cover-Annual' ) then 5000\n" +
"          WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Single Cover -Monthly'  then (600 * 6)\n" +
"          WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Multiple Cover -Monthly'  then (600 * 6)\n" +
"          WHEN J.Tenor IS null and J.field_id = 51 and J.Premium_Type = 'Child Cover-Monthly'  then (550 * 6)\n" +
"\n" +
"     END 'AXA_Premium'\n" +
"\n" +
"FROM\n" +
" \n" +
"(SELECT distinct rm.branch_no,dp.rim_no,dp.title_1,hist.acct_no,fld.field_label 'Insurance_Type',gb.[value] 'Premium_Type', hist.effective_dt,\n" +
"--gb.create_dt'Offer_Date',\n" +
" \n" +
"hist.amt,\n" +
"hist.description,\n" +
"/*\n" +
"(select ln.trm from ln_display ln where ln.rim_no = dp.rim_no and ln.status = 'active' and ln.class_code <> 701\n" +
"and ln.create_dt = (select max(ln_disp2.create_dt) from ln_display ln_disp2 where ln_disp2.status = 'active' \n" +
"and ln_disp2.class_code <> 701\n" +
"and ln_disp2.acct_no = ln.acct_no))'Tenor',*/\n" +
"ln.trm'Tenor',\n" +
"gb.field_id\n" +
"FROM dp_acct dp \n" +
"JOIN rm_acct rm ON dp.rim_no = rm.rim_no\n" +
"join dp_history hist on dp.acct_no = hist.acct_no\n" +
"JOIN gb_user_defined gb ON cast(gb.acct_no_key as int) = dp.rim_no\n" +
"join ad_gb_user_fld fld on fld.field_id = gb.field_id\n" +
"join ad_gb_user_fld_lst fld_lst on fld_lst.field_id = fld.field_id\n" +
"left outer join ln_display ln on dp.rim_no = ln.rim_no and ln.status = 'active' and ln.class_code <> 701\n" +
"                              and ln.create_dt = (select max(ln_disp2.create_dt) from ln_display ln_disp2 \n" +
"                              where ln_disp2.status = 'active' \n" +
"                              and ln_disp2.class_code <> 701\n" +
"                              and ln_disp2.acct_no = ln.acct_no)\n" +
"where dp.class_code <> 191\n" +
"AND dp.acct_type <> 'CT'\n" +
"and hist.tran_code = 152\n" +
"and dp.status in ('active','restricted')\n" +
"AND gb.field_id in (52,51) and gb.value <> 'None'\n" +
"--and rm.rim_no not in (128757,116958,126839,14157,1109344)\n" +
"/*and rm.rim_no not in (121387,120947, 94242,131053,92433,106990,51224,133884,122975,123527,\n" +
"                        122849,15064,121024,113075,122301,3067,61546,119278,128356,109448,\n" +
"                        127167,117288,117907,114969,92776,127136,127405,106123,124951,112008,104969,136290)*/\n" +
"and fld_lst.list_id in (168,169,170,171,172,173,174,175,176,177,178,179)\n" +
"and hist.description like '%Insurance %')J\n" +
"where J.effective_dt between '3/1/2024' and '3/8/2024'\n" +
"--<:and: J.branch_no:2>\n" ;
    
    
    public static String loanRequest= 
"select ln.rim_no as ln_rim,\n" +
"\n" +
"(select r.branch_no from rm_acct r where r.rim_no=ln.rim_no) as branch_no,\n" +
"\n" +
"ln.acct_no,\n" +
"\n" +
"ln.acct_type,\n" +
"\n" +
"ln.class_code,\n" +
"\n" +
"ln.status,\n" +
"\n" +
"dp.rim_no as dp_rim,\n" +
"\n" +
"pm.tfr_acct_no\n" +
"\n" +
"from ln_acct ln,ln_pmt_schedule pm, dp_acct dp\n" +
"\n" +
"where ln.acct_no =pm.acct_no\n" +
"\n" +
"and dp.acct_no = pm.tfr_acct_no\n" +
"\n" +
"and ln.rim_no != dp.rim_no\n" +
"\n" +
"and pm.tfr_acct_no is not null\n" +
"\n" +
"and ln.status not in ('Incomplete')\n" +
"\n" +
"UNION ALL\n" +
"\n" +
"select ln.rim_no as ln_rim,\n" +
"\n" +
"(select r.branch_no from rm_acct r where r.rim_no=ln.rim_no) as branch_no,\n" +
"\n" +
"ln.acct_no,\n" +
"\n" +
"ln.acct_type,\n" +
"\n" +
"ln.class_code,\n" +
"\n" +
"ln.status,\n" +
"\n" +
"null dp_rim,\n" +
"\n" +
"pm.tfr_acct_no\n" +
"\n" +
"from ln_acct ln,ln_pmt_schedule pm\n" +
"\n" +
"where ln.acct_no =pm.acct_no\n" +
"\n" +
"and pm.tfr_acct_no is null\n" +
"\n" +
"and ln.status not in ('Incomplete')";
    
    
    public static String q = "select branch_no,acct_no\n" +
",convert (char(8),mat_dt,3)'MaturityDate'\n" +
",status,tfr_int_acct_no'tfr_acct_no' from dp_acct\n" +
"where status = 'active' --and tfr_int_acct_no <>null\n" +
"and  acct_type = 'ta'\n" +
"and mat_dt >= (select last_to_dt from ov_control)";
    
   // public static String query = "select rac.last_name as 'AccountName', (select gdef.[value] from gb_user_defined gdef where lac.acct_no = gdef.acct_no_key and field_id = 45  and cls_id=lac.class_code ) 'AccountNumber', (SELECT str_replace(rtrim(CONVERT(VARCHAR(11),rmact2.birth_dt,112)),\" \",\"-\") from rm_acct rmact2, rm_rel rr     WHERE rmact2.rim_no = rr.rel_rim_no AND rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'DateOfBirth', rad.email_addr_1 as 'email',(SELECT rmact2.first_name from rm_acct rmact2, rm_rel rr  WHERE rmact2.rim_no = rr.rel_rim_no AND rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'FirstName',  (SELECT rmact2.middle_initial from rm_acct rmact2, rm_rel rr   WHERE rmact2.rim_no = rr.rel_rim_no AND rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'MiddleName', (SELECT rmact2.last_name from rm_acct rmact2, rm_rel rr WHERE rmact2.rim_no = rr.rel_rim_no AND rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'LastName', lac.acct_no 'OldAccounttNumber', rad.phone_3 'PhoneNumber', CASE WHEN rac.rim_type = 'NonPersonal' THEN '1' WHEN rac.rim_type = 'Personal' and (datediff(yy,rac.birth_dt,(SELECT last_to_dt from ov_control)) >= 18) THEN '2' WHEN rac.rim_type = 'Personal' and (datediff(yy,rac.birth_dt,(SELECT last_to_dt from ov_control)) < 18) THEN '5'  END 'AccountDesignation', CASE WHEN lac.status = 'Active' THEN '1' WHEN lac.status = 'Dormant' THEN '2' WHEN lac.status = 'Closed' THEN '3' WHEN lac.status = 'restricted' THEN '4'  WHEN lac.status = 'Locked' THEN '5'  WHEN lac.status = 'Inactive' THEN '6' WHEN lac.status = 'Escheated' THEN '7'  ELSE '8' END 'AccountStatus', CASE WHEN lac.acct_type = 'CA' THEN '1' WHEN lac.acct_type = 'SA' THEN '2' ELSE '3' END 'AccountType', (select gdef.[value] from gb_user_defined gdef where rac.rim_no = cast(gdef.acct_no_key as int) and field_id = 44 and cls_id=rac.class_code )'BVN', 'N' as 'PEP', sic.sic_code 'SectorCode' ,lac.branch_no, CASE WHEN lac.class_code = 211 THEN 'Tier_1' ELSE 'Tier_3' END 'Account Tier' from rm_acct rac, dp_acct lac,rm_address rad, rm_address rad2 ,rm_personal_info rpi ,ad_gb_sic sic,ad_rm_title title where rac.rim_no=rad.rim_no and rad.rim_no =lac.rim_no AND rac.rim_no *= rad2.rim_no and rad2.rim_no =*lac.rim_no and lac.rim_no = rpi.rim_no and rac.sic_code = sic.sic_code and title.title_id = rac.title_id and rad.addr_id = 1 AND rad2.addr_id = 2 and lac.acct_type <> 'ct' and rac.rim_type = 'nonpersonal' and lac.effective_dt >= '2/2/2024' order by rac.rim_no";
public static String query = "select  \n" +
"rac.last_name as 'AccountName',\n" +
" \n" +
" \n" +
"(select gdef.[value] from gb_user_defined gdef where lac.acct_no = gdef.acct_no_key and field_id = 45 \n" +
" \n" +
"and cls_id=lac.class_code\n" +
") 'AccountNumber',\n" +
" \n" +
"(SELECT str_replace(rtrim(CONVERT(VARCHAR(11),rmact2.birth_dt,112)),' ',' ') from rm_acct rmact2, rm_rel rr \n" +
"   WHERE rmact2.rim_no = rr.rel_rim_no AND \n" +
"   rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'DateOfBirth',\n" +
" \n" +
"rad.email_addr_1 as 'email',\n" +
"(SELECT rmact2.first_name from rm_acct rmact2, rm_rel rr \n" +
"   WHERE rmact2.rim_no = rr.rel_rim_no AND \n" +
"   rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'FirstName',\n" +
"   (SELECT rmact2.middle_initial from rm_acct rmact2, rm_rel rr \n" +
"   WHERE rmact2.rim_no = rr.rel_rim_no AND \n" +
"   rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'MiddleName',\n" +
"(SELECT rmact2.last_name from rm_acct rmact2, rm_rel rr \n" +
"   WHERE rmact2.rim_no = rr.rel_rim_no AND \n" +
"   rr.rim_no = rac.rim_no AND rr.rel_rel_id IN (40) and rr.status IN ('active')) 'LastName',\n" +
"\n" +
" \n" +
"lac.acct_no 'OldAccounttNumber',\n" +
"rad.phone_3 'PhoneNumber',\n" +
" \n" +
"CASE \n" +
" WHEN rac.rim_type = 'NonPersonal' THEN '1'\n" +
" WHEN rac.rim_type = 'Personal' and (datediff(yy,rac.birth_dt,(SELECT last_to_dt from ov_control)) >= 18) THEN '2'\n" +
" WHEN rac.rim_type = 'Personal' and (datediff(yy,rac.birth_dt,(SELECT last_to_dt from ov_control)) < 18) THEN '5'\n" +
"  END 'AccountDesignation',\n" +
"\n" +
"CASE \n" +
" WHEN lac.status = 'Active' THEN '1'\n" +
" WHEN lac.status = 'Dormant' THEN '2'\n" +
" WHEN lac.status = 'Closed' THEN '3'\n" +
" WHEN lac.status = 'restricted' THEN '4'\n" +
"  WHEN lac.status = 'Locked' THEN '5'\n" +
"  WHEN lac.status = 'Inactive' THEN '6'\n" +
"  WHEN lac.status = 'Escheated' THEN '7'\n" +
"  ELSE '8'\n" +
" END 'AccountStatus',\n" +
"\n" +
"CASE \n" +
" WHEN lac.acct_type = 'CA' THEN '1'\n" +
" WHEN lac.acct_type = 'SA' THEN '2'\n" +
" ELSE '3'\n" +
" END 'AccountType',\n" +
"(select gdef.[value] from gb_user_defined gdef where rac.rim_no = cast(gdef.acct_no_key as int) and field_id = 44 \n" +
" \n" +
"and cls_id=rac.class_code\n" +
") 'BVN',\n" +
"'N' as 'PEP',\n" +
"sic.sic_code 'SectorCode'\n" +
",lac.branch_no,\n" +
"CASE \n" +
"WHEN lac.class_code = 211 THEN 'Tier_1'\n" +
"ELSE 'Tier_3'\n" +
"END 'Account Tier'--Added on request of CBN\n" +
"from rm_acct rac, dp_acct lac,rm_address rad, rm_address rad2--,ad_rm_employment adr\n" +
",rm_personal_info rpi ,ad_gb_sic sic,ad_rm_title title\n" +
"where rac.rim_no=rad.rim_no and rad.rim_no =lac.rim_no\n" +
"AND rac.rim_no *= rad2.rim_no and rad2.rim_no =*lac.rim_no\n" +
"and lac.rim_no = rpi.rim_no\n" +
" \n" +
"and rac.sic_code = sic.sic_code\n" +
"and title.title_id = rac.title_id\n" +
" \n" +
"and rad.addr_id = 1\n" +
"AND rad2.addr_id = 2\n" +
"and lac.acct_type <> 'ct'\n" +
"and rac.rim_type = 'nonpersonal'\n" +
"and lac.effective_dt >= '2/2/2024'\n" +
" \n" +
"order by rac.rim_no\n" +
" \n" ;


public static String ndic = 
"select distinct rm.rim_no'SCVID',\n" +
"\n" +
"gb.[value]'CustomerBVN',\n" +
"\n" +
"adm.identification'NationalIDType',\n" +
"\n" +
"rm.id_value'NationalIDNo',\n" +
"\n" +
"dp.acct_no'AccountNumber',\n" +
"\n" +
"CASE when dp.acct_type ='CA' THEN 'Current'\n" +
"\n" +
"when dp.acct_type in ('SA','CT') then 'Savings'\n" +
"\n" +
"when dp.acct_type ='TA' then 'Fixed'\n" +
"\n" +
"end 'Account Type',\n" +
"\n" +
"CASE when rmcls.class_code = 911 THEN 'Bank Staff'\n" +
"\n" +
"else 'Other customers'\n" +
"\n" +
"end 'Category of Account',\n" +
"\n" +
"rm.last_name'LastName',\n" +
"\n" +
"rm.middle_initial'MIddleName',\n" +
"\n" +
"rm.first_name'FirstName',\n" +
"\n" +
"art.title'AccountOwnersTitle',\n" +
"\n" +
"rm.birth_dt'DateOfBirth',\n" +
"\n" +
"rmd.phone_3'MobileNo',\n" +
"\n" +
"rmd.address_line_1'CustomerContactAddress',\n" +
"\n" +
"dp.cur_bal'AccountBalance',\n" +
"\n" +
"dp.status,\n" +
"\n" +
"/*(select sum(ln2.lien_amt)from ln_collateral ln2, rm_fin_stmt fn\n" +
"\n" +
"  where ln2.fin_stmt_item_id = fn.fin_stmt_item_id and fn.rim_no=rm.rim_no and fn.status in ('active'))*/\n" +
"\n" +
"  isnull((select sum(dpy.cur_bal) from dp_display dpy where dpy.acct_type = 'CT' and dpy.class_code = 811 \n" +
"\n" +
"        and rm.rim_no= dpy.rim_no and dpy.status not in ('closed')),0)'PledgedAsCollateral',\n" +
"\n" +
"        (select sum (dp1.cur_bal) from dp_display dp1 where dp1.rim_no= dp.rim_no and dp1.status not in ('closed'))'TotalBalance',\n" +
"\n" +
"CASE when ln.acct_type in ('LG','LI','LSE') THEN 'Term'\n" +
" \n" +
"end 'LoanType',\n" +
" \n" +
"ln.contract_dt'DateGranted',\n" +
"\n" +
"ln.amt'LoanAmount',\n" +
"\n" +
"ln.cur_bal'LoanOutstanding',\n" +
"\n" +
"(select sum (lb2.amt) from ln_bill lb2 where lb2.sub_no=2 and lb2.acct_no =ln.acct_no)'Principal',\n" +
"\n" +
"(select sum (lb2.amt) from ln_bill lb2 where lb2.sub_no=1 and lb2.acct_no =ln.acct_no)'Interest',\n" +
"\n" +
"(select sum (ln1.amt) from ln_history ln1 where ln1.tran_code =333  and ln1.acct_no=ln.acct_no)'WaiverWriteOff',\n" +
"\n" +
"0 as CashBacked,\n" +
"\n" +
"0 as CashAmount,\n" +
"\n" +
"  CASE WHEN rfs.fin_stmt_type is not null then 'YES' end\n" +
"\n" +
"'Secured',\n" +
"\n" +
"rfs.fin_stmt_type'CollateralType', \n" +
"\n" +
"ln_col.lien_amt'CollateralValue',\n" +
"\n" +
"rfs.description'CollateralDescription',\n" +
"\n" +
"rfs.address_line_1'CollateralFullAddress',\n" +
"\n" +
"'Not Perfected'as'CollateralStatus',\n" +
"\n" +
"rm2.last_name+''+rm2.first_name'GuarantorsName',\n" +
"\n" +
"gb_guar.[value]'GuarrantorsBVN',\n" +
"\n" +
"rm2.id_value 'GuarrantorAddNationalIDNo',\n" +
"\n" +
"rm_ad.address_line_1'GuarrantorsAddress',\n" +
"\n" +
"rm_ad.phone_3'GuarrantorsPhoneNumber',\n" +
"\n" +
"isnull((select sum (ln2.cur_bal) from ln_display ln2 where ln2.status not in ('closed','incomplete','active charge-off')and ln2.rim_no =ln.rim_no),0)'AggregateLoanBalance'\n" +
"\n" +
"from rm_acct rm, gb_user_defined gb,ad_rm_ident adm,dp_display dp,ad_rm_title art,\n" +
"\n" +
"rm_address rmd,ln_display ln,ln_collateral ln_col,rm_fin_stmt rfs,rm_rel rel,rm_acct rm2,rm_address rm_ad,\n" +
"\n" +
"gb_user_defined gb_guar, ad_rm_cls rmcls\n" +
"\n" +
"where rm.rim_no *= cast(gb.acct_no_key as int)\n" +
"\n" +
"and rm.ident_id *= adm.ident_id\n" +
"\n" +
"and rm.rim_no *= dp.rim_no\n" +
"\n" +
"and rm.title_id = art.title_id\n" +
"\n" +
"and rm.rim_no = rmd.rim_no\n" +
"\n" +
"and rm.rim_no *= ln.rim_no\n" +
"\n" +
"and rm.rim_no *= ln_col.rim_no\n" +
"\n" +
"and rm.rim_no *=rfs.rim_no\n" +
"\n" +
"and rm.rim_no *= rel.rim_no\n" +
"\n" +
"and rel.rel_rim_no *= rm2.rim_no\n" +
"\n" +
"and rm2.rim_no *= cast(gb_guar.acct_no_key as int)\n" +
"\n" +
"and rm2.rim_no *= rm_ad.rim_no\n" +
"\n" +
"and gb_guar.field_id = 44\n" +
"\n" +
"and ln_col.fin_stmt_item_id *= rfs.fin_stmt_item_id\n" +
"\n" +
"and rm_ad.addr_id = 1\n" +
"\n" +
"and gb_guar.acct_no_key not like '%E+5%'\n" +
"\n" +
"and rel.rel_rel_id IN (32)\n" +
"\n" +
"and rel.status in ('active')\n" +
"\n" +
"and rmd.addr_id = 1\n" +
"\n" +
"and gb.field_id = 44\n" +
"\n" +
"and ln_col.status in ('active')\n" +
"\n" +
"and gb.acct_no_key not like '%E+5%'\n" +
"\n" +
"and rm.status in ('active')\n" +
"\n" +
"and dp.status not in ('closed')\n" +
"\n" +
"and ln.status not in ('incomplete','closed')\n" +
"\n" +
"and rm.class_code not in (10)\n" +
"\n" +
"and rm.rim_type ='Personal'\n" +
"\n" +
"and rmcls.class_code = rm.class_code\n" +
"\n" +
"and rm.rim_no between 40001 and 80000 --This is to pick the rim range \n" +
"\n" +
"order by rim_no";
}
