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
public class InstantaQuery {
    
    public static String query= "select lh.branch_no,lh.rim_no,rc.description'Rim_Class',lh.acct_no,lh.amt,lh.status,lh.first_adv_dt,lh.closed_dt\n" +
",(select count(*) from ln_acct l2 where l2.rim_no=lh.rim_no and l2.status<>'incomplete' and l2.class_code <>571) 'cycle'\n" +
",lh.acct_type\n" +
",cls.description,la.class_code\n" +
",la.title_1 'Name'\n" +
",agr.name 'RSM_on_RIM'\n" +
"from ln_display lh, ad_ln_cls cls, ln_acct la, ln_history ln,rm_acct ra,\n" +
"ad_rm_cls rc,ad_gb_rsm agr\n" +
"where lh.class_code in (531,541) \n" +
"and cls.class_code = lh.class_code\n" +
"and ln.acct_no = lh.acct_no\n" +
"and la.acct_no=lh.acct_no\n" +
"and ra.rim_no =lh.rim_no\n" +
"and ra.class_code = rc.class_code\n" +
"and ln.tran_code in (350)\n" +
"and ln.reversal = 0\n" +
"and agr.employee_id = ra.rsm_id\n" +
"order by la.rim_no";
 
     public static String todayDate(){
        Calendar c = Calendar.getInstance();
        int day= c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1 ;
         int year = c.get(Calendar.YEAR);
        return (day+""+month+""+year);
    }
    
}
