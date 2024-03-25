/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportExtraction;

import java.util.Calendar;

/**
 *
 * @author dakinkuolie
 */
public class ndicQuery {
    public static String ndicQUery = "select distinct rm.rim_no'SCVID',\n" +
"\n" +
"dp.acct_no'AccountNumber',\n" +
"\n" +
"rm.last_name'Account Name',\n" +
"\n" +
"CASE when dp.acct_type ='CA' THEN 'Current'\n" +
"\n" +
"when dp.acct_type in ( 'SA','CT')then 'Savings'\n" +
"\n" +
"when dp.acct_type ='TA' then 'Fixed'\n" +
"\n" +
"--when dp.acct_type ='CT' then 'Technical'\n" +
"\n" +
"end 'Account Type',\n" +
"\n" +
"'Private' as 'Category of Account',--rmcls.description ,*/\n" +
"\n" +
"rm.tin 'TIN',\n" +
"\n" +
"CASE when (select rma.id_value from rm_acct rma where rma.ident_id = 25 and rma.rim_no = rm.rim_no)is null then 'N/A'\n" +
"\n" +
"ELSE (select rma.id_value from rm_acct rma where rma.ident_id = 25 and rma.rim_no = rm.rim_no)\n" +
"\n" +
"END'Corporate_Customer_RC_No',\n" +
"\n" +
"rmrep.last_name+' '+rmrep.first_name'Name of Chief Executive',\n" +
"\n" +
"gb.[value]'BVN of Chief Executive',\n" +
"\n" +
"rmd2.phone_3'MobileNo of Chief Executive',\n" +
"\n" +
"rmd.address_line_1'Contact Address of the Entity',\n" +
"\n" +
"rmd.phone_3'Office Phone Number',\n" +
"\n" +
"dp.cur_bal'AccountBalance', \n" +
"\n" +
"dp.status,\n" +
"\n" +
"isnull((select sum(dpy.cur_bal) from dp_display dpy where dpy.acct_type = 'CT' and dpy.class_code = 811 \n" +
"\n" +
"        and rm.rim_no= dpy.rim_no and dpy.status not in ('closed')),0)'PledgedAsCollateral',\n" +
"\n" +
"(select sum (dp1.cur_bal) from dp_display dp1 where dp1.rim_no= dp.rim_no and dp1.status not in ('closed'))'TotalBalance',\n" +
"\n" +
"'Term'as 'LoanType',\n" +
"\n" +
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
"  CASE WHEN rfs.fin_stmt_type is not null then 'YES' end\n" +
"\n" +
"'Secured',\n" +
"\n" +
"0 as CashBacked,\n" +
"\n" +
"0 as CashAmount,\n" +
"\n" +
"rfs.fin_stmt_type'CollateralType', --rfs.description'CollateralDescription',\n" +
"\n" +
"ln_col.lien_amt'CollateralValue',\n" +
"\n" +
"rfs.address_line_1'Collateral Location',\n" +
"\n" +
"'Not Perfected' as 'CollateralStatus',\n" +
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
"rm_address rmd,ln_display ln,ln_collateral ln_col,rm_fin_stmt rfs,rm_rel rel,rm_acct rm2,rm_address rm_ad, ad_rm_cls rmcls,\n" +
"\n" +
"gb_user_defined gb_guar, rm_acct rmrep, rm_rel relrep, rm_address rmd2\n" +
"\n" +
"where rm.rim_no *= dp.rim_no\n" +
"\n" +
"and rm.ident_id *= adm.ident_id\n" +
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
"and ln_col.fin_stmt_item_id *= rfs.fin_stmt_item_id\n" +
"\n" +
"and gb.acct_no_key not like '%E+5%'\n" +
"\n" +
"and rm.status in ('active')\n" +
"\n" +
"and dp.status not in ('closed')\n" +
"\n" +
"--and dp.acct_type not in ('ct')\n" +
"\n" +
"and ln.status not in ('incomplete','closed')\n" +
"\n" +
"and rm.class_code not in (10)\n" +
"\n" +
"--and dp.class_code not in (801,191)\n" +
"\n" +
"and rm.rim_type ='NonPersonal'\n" +
"\n" +
"and rmrep.rim_no *= cast(gb.acct_no_key as int)\n" +
"\n" +
"and rm.rim_no *= relrep.rim_no\n" +
"\n" +
"and relrep.rel_rim_no *= rmrep.rim_no\n" +
"\n" +
"and relrep.rel_rel_id IN (40)\n" +
"\n" +
"and relrep.status in ('active')\n" +
"\n" +
"and rmrep.rim_no *= rmd2.rim_no\n" +
"\n" +
"and rmd2.addr_id = 1\n" +
"\n" +
"and rmcls.class_code = rm.class_code\n" +
"\n" +
"--and rm.rim_no in (136663,124926, 26, 623)\n" +
"\n" +
"--and rm.rim_no between 1 and 1000\n" +
"\n" +
"order by rim_no\n";
    
     public static String todayDate(){
        Calendar c = Calendar.getInstance();
        int day= c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1 ;
         int year = c.get(Calendar.YEAR);
        return (day+""+month+""+year);
    }
}
