package com.example.timxekhach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.timxekhach.DBXe.Xe;
import com.example.timxekhach.DBUser.User;
import com.example.timxekhach.Database.SQLXe;
import com.example.timxekhach.Database.SQLUser;

import java.util.ArrayList;

public class LoginActivity extends Activity {

    public static final String EXTRA_USER = "com.example.application.example.EXTRA_USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        final EditText password = (EditText) findViewById(R.id.pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button login = (Button) findViewById(R.id.login);
        final Button signup = (Button) findViewById(R.id.SignUp);
        final Button quenmk = (Button) findViewById(R.id.quenmk);
        final ImageView logo= (ImageView) findViewById(R.id.logo);

        SpannableString ssdk = new SpannableString("Đăng kí");
        ssdk.setSpan(new UnderlineSpan(), 0, 7, 0);
        signup.setText(ssdk);
        SpannableString ssqmk = new SpannableString("Quên mật khẩu?");
        ssqmk.setSpan(new UnderlineSpan(), 0, 14, 0);
        quenmk.setText(ssqmk);
        logo.setImageResource(R.drawable.logo);

        final SQLUser sqlUser = new SQLUser(this);
        ArrayList<User> list = new ArrayList<>();

        User s = new User("hathiep", "Hà Văn Thiệp", "hathiep2308@gmail.com", "Hvtmk12@", "Hành Khách");
        sqlUser.AddUser(s);

        ArrayXe();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pass = password.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText( LoginActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                }else{
                    User s = sqlUser.getUser(name);
                    if(s != null){
                        if(s.getPassword().equals(pass)){
                            Toast.makeText( LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                            password.setText("");
                            username.setText("");
                            Login(s);
                        }else {
                            password.setText("");
                            Toast.makeText( LoginActivity.this, "Mật khẩu không chính xác!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        password.setText("");
                        username.setText("");
                        Toast.makeText( LoginActivity.this, "Tài khoản không tồn tại!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenForgotPassword();
            }
        });
    }

    public void ArrayXe(){
        SQLXe sqlXe = new SQLXe(this);
        ArrayList<Xe> listxe = new ArrayList<>();
        listxe.add(new Xe(1, "Hải Âu", "15B-015.76", "Xe Khách", "Xanh Dương", "0982066820", "100.000-120.000đ", "Hải Phòng - Hà Nội", "6h30-9h00" , "06h30: Văn Phòng An Lão\n06h50: Bến xe Thượng Lý\n07h05: Văn Phòng Quán Toan\n07h20: Bến xe Vĩnh Niệm\n08h30: Bến xe Gia Lâm\n08h50: Bến xe Nước Ngầm", R.drawable.anh1, 24));
        listxe.add(new Xe(2, "An Phát", "29B-136.54", "Xe Giường Nằm", "Xanh Lá Cây", "0942665088", "80.000-100.000đ", "Nam Định - Hà Nội", "12h30h-15h30h","12h30: Bến xe Đông Bình\n12h35: Nghĩa Bình\n12h55: Liễu Đề\n13h15: Tam Thôn\n13h35: Chợ Chùa\n15h20: Bến xe Giáp Bát" ,R.drawable.anh2, 29));
        listxe.add(new Xe(3, "Nam Hưng", "29B-124.68", "Xe Khách", "Đen", "0836380626", "120.000-150.000đ", "Nam Định - Hà Nội", "17h00-20h00","17h00: Bến xe Liễu Đề\n17h15: Tam Thôn\n17h25: Chợ Chùa\n18h30: Bến xe Nước Ngầm\n18h50: Văn Điển\n19h10: KĐT Xa La\n19h25: Văn Phú\n19h45: Ngã ba Ba La\n20h00: Bến xe Yên Nghĩa" ,R.drawable.anh3, 16));
        listxe.add(new Xe(4, "A Lâm  ", "72B-026.99", "Xe Khách", "Trắng", "0924272829", "80.000-120.000đ", "Bà Rịa - Vũng Tàu - Thành Phố Hồ Chí Minh", "16h00-18h00" , "\n16h00: Văn Phòng Ngãi Giao\n16h50: Bến xe Hắc Dịch\n17h20: Văn Phòng Quán Toan\n18h00: Trạm xe thành phố:Số 03, Trần Phú, Phường 4, Quận 5, TP.HCM", R.drawable.anh4, 30));
        listxe.add(new Xe(5, "Đồng Hương Sông Lam", "29B-004.89", "Xe Giường Nằm", "Vàng", "0969037123", "200.000-300.000đ", "Nghệ An - Hà Nội", "07h00-12h00","07h00: Thị xã Cửa Lò:\n07h30: Nội thành TPVinh\n08h30: Hoàng Mai\n11h30: Bến Xe Nước Ngầm\n12h00: MĐ - 87 Dương Khuê" ,R.drawable.anh5, 32));
        listxe.add(new Xe(6, "Duy Khang", "29B-413.09", "Xe Limousine", "Đen", "19003086", "150.000đ", "Ninh Bình - Hà Nội", "18h00-19h45","18h00:BigC Ninh Bình\n18h15: Văn Phòng Ninh Bình\n19h00: Công viên Cầu giấy\n19h10: Văn Phòng 93 Phố Vọng \n19h30: VP Rạp Xiếc (79 Trần Nhân Tông)" ,R.drawable.anh6, 11));
        listxe.add(new Xe(7, "Hùng Đức", "14F-004.27", "Xe Limousine", "Trắng", "0938466820", "140.000đ-170.000đ", "Hà Nội - Quảng Ninh", "13h30-17h10" , "13h30: Bến xe Mỹ Đình\n13h50: Công Viên Hòa Bình\n017h00: Ngã ba Cửa Ông\n17h10: Khu vực Cẩm Phả", R.drawable.anh7, 16));
        listxe.add(new Xe(8, "Hoàng Long", "51B-088.80", "Xe Giường Nằm", "Xanh Lá Cây", "0942541785", "1.100.000đ", "Thành Phố Hồ Chí Minh - Hà Nội", "13h00-1h00","13h00: Bến xe Miền Đông Mới\n13h25: Trạm Xăng Dầu Huệ Thiên 3\n13h55: Cầu Đồng Nai\n14h20: Nhà thờ Trà Cổ\n15h35: Ngã 3 Ông Đồn\n01h00: Bến xe Nước Ngầm" ,R.drawable.anh8, 40));
        listxe.add(new Xe(9, "Thiên Kim", "86F-020.37", "Xe Limousine", "Trắng", "0965480636", "230.000-280.000đ", "Bình Thuận - Thành Phố Hồ Chí Minh", "13h00-16h45","13h00: Bến xe Bắc Ruộng\n13h15: Cây xăng Thanh Lợi 2\n13h45: Xã Đức Hạnh\n15h30: Vòng xoay Điện Biên Phủ\n16h45: Sân bay Tân Sơn Nhất" ,R.drawable.anh9, 12));
        listxe.add(new Xe(10, "Xuân Quỳnh", "37B-014.64", "Xe Khách", "Xanh Dương", "0984268240", "150.000đ", "Hải Phòng - Hà Nội", "14h00-15h50" , "14h00: Bến xe Thượng Lý\n14h15: Văn phòng Quán Toan\n14h35: Văn phòng An Lão\n15h50: Bến xe Mỹ Đình", R.drawable.anh10, 24));
        listxe.add(new Xe(11, "Hải Âu", "15B-015.76", "Xe Khách", "Xanh Dương", "0982066820", "100.000-120.000đ", "Hà Nội - Hải Phòng", "6h30-9h00" , "06h30: Văn Phòng An Lão\n06h50: Bến xe Thượng Lý\n07h05: Văn Phòng Quán Toan\n07h20: Bến xe Vĩnh Niệm\n08h30: Bến xe Gia Lâm\n08h50: Bến xe Nước Ngầm", R.drawable.anh1, 24));
        listxe.add(new Xe(12, "An Phát", "29B-136.54", "Xe Giường Nằm", "Xanh Lá Cây", "0942665088", "80.000-100.000đ", "Hà Nội - Nam Định", "12h30h-15h30h","12h30: Bến xe Đông Bình\n12h35: Nghĩa Bình\n12h55: Liễu Đề\n13h15: Tam Thôn\n13h35: Chợ Chùa\n15h20: Bến xe Giáp Bát" ,R.drawable.anh2, 29));
        listxe.add(new Xe(13, "Nam Hưng", "29B-124.68", "Xe Khách", "Đen", "0836380626", "120.000-150.000đ", "Hà Nội - Nam Định", "17h00-20h00","17h00: Bến xe Liễu Đề\n17h15: Tam Thôn\n17h25: Chợ Chùa\n18h30: Bến xe Nước Ngầm\n18h50: Văn Điển\n19h10: KĐT Xa La\n19h25: Văn Phú\n19h45: Ngã ba Ba La\n20h00: Bến xe Yên Nghĩa" ,R.drawable.anh3, 16));
        listxe.add(new Xe(14, "A Lâm  ", "72B-026.99", "Xe Khách", "Trắng", "0924272829", "80.000-120.000đ", "Thành Phố Hồ Chí Minh - Bà Rịa - Vũng Tàu", "16h00-18h00" , "16h00: Văn Phòng Ngãi Giao\n16h50: Bến xe Hắc Dịch\n17h20: Văn Phòng Quán Toan\n18h00: Trạm xe thành phố:Số 03, Trần Phú, Phường 4, Quận 5, TP.HCM", R.drawable.anh4, 30));
        listxe.add(new Xe(15, "Đồng Hương Sông Lam", "29B-004.89", "Xe Giường Nằm", "Vàng", "0969037123", "200.000-300.000đ", "Hà Nội - Nghệ An", "07h00-12h00","07h00: Thị xã Cửa Lò:\n07h30: Nội thành TPVinh\n08h30: Hoàng Mai\n11h30: Bến Xe Nước Ngầm\n12h00: MĐ - 87 Dương Khuê" ,R.drawable.anh5, 32));
        listxe.add(new Xe(16, "Duy Khang", "29B-413.09", "Xe Limousine", "Đen", "19003086", "150.000đ", "Hà Nội - Ninh Bình", "18h00-19h45","18h00:BigC Ninh Bình\n18h15: Văn Phòng Ninh Bình\n19h00: Công viên Cầu giấy\n19h10: Văn Phòng 93 Phố Vọng \n19h30: VP Rạp Xiếc (79 Trần Nhân Tông)" ,R.drawable.anh6, 11));
        listxe.add(new Xe(17, "Hùng Đức", "14F-004.27", "Xe Limousine", "Trắng", "0938466820", "140.000đ-170.000đ", "Quảng Ninh - Hà Nội", "13h30-17h10" , "13h30: Bến xe Mỹ Đình\n13h50: Công Viên Hòa Bình\n017h00: Ngã ba Cửa Ông\n17h10: Khu vực Cẩm Phả", R.drawable.anh7, 16));
        listxe.add(new Xe(18, "Hoàng Long", "51B-088.80", "Xe Giường Nằm", "Xanh Lá Cây", "0942541785", "1.100.000đ", "Hà Nội - Thành Phố Hồ Chí Minh", "13h00-1h00","13h00: Bến xe Miền Đông Mới\n13h25: Trạm Xăng Dầu Huệ Thiên 3\n13h55: Cầu Đồng Nai\n14h20: Nhà thờ Trà Cổ\n15h35: Ngã 3 Ông Đồn\n01h00: Bến xe Nước Ngầm" ,R.drawable.anh8, 40));
        listxe.add(new Xe(19, "Thiên Kim", "86F-020.37", "Xe Limousine", "Trắng", "0965480636", "230.000-280.000đ", "Thành Phố Hồ Chí Minh - Bình Thuận", "13h00-16h45","13h00: Bến xe Bắc Ruộng\n13h15: Cây xăng Thanh Lợi 2\n13h45: Xã Đức Hạnh\n15h30: Vòng xoay Điện Biên Phủ\n16h45: Sân bay Tân Sơn Nhất" ,R.drawable.anh9, 12));
        listxe.add(new Xe(20, "Xuân Quỳnh", "37B-014.64", "Xe Khách", "Xanh Dương", "0984268240", "150.000đ", "Hà Nội - Hải Phòng", "14h00-15h50" , "14h00: Bến xe Thượng Lý\n14h15: Văn phòng Quán Toan\n14h35: Văn phòng An Lão\n15h50: Bến xe Mỹ Đình", R.drawable.anh10, 24));
        listxe.add(new Xe(11, "Hải Âu", "15B-015.76", "Xe Giường Nằm", "Trắng", "0982066820", "100.000-120.000đ", "Hà Nội - Hải Phòng", "11h30-14h00" , "11h30: Bến xe Nước Ngầm\n11h50: Bến xe Gia Lâm\n13h00:  Bến xe Vĩnh Niệm\n13h15: Văn Phòng Quán Toan\n13h30: Bến xe Thượng Lý\n14h00: Văn Phòng An Lão", R.drawable.anh1, 24));
        listxe.add(new Xe(12, "An Phát", "29B-136.54", "Xe Khách", "Xanh Đen", "0942665088", "80.000-100.000đ", "Hà Nội - Nam Định", "17h00-20h00","17h00: Bến xe Giáp Bát\n18h30: Chợ Chùa\n19h00: Tam Thôn\n19h25:Liễu Đề\n19h35: Nghĩa Bình\n15h20: Bến xe Đông Bình" ,R.drawable.anh2, 29));
        listxe.add(new Xe(13, "Nam Hưng", "29B-124.68", "Xe Limousine", "Trắng", "0836380626", "120.000-150.000đ", "Hà Nội - Nam Định", "06h00-09h00","06h00:Bến xe Yên Nghĩa \n06h15:Ngã ba Ba La\n06h40: Văn Phú\n07h00: KĐT Xa La\n7h20: Chợ Chùa\n08h15: Tam Thôn\n09h00: Bến xe Liễu Đề" ,R.drawable.anh3, 36));
        listxe.add(new Xe(14, "A Lâm  ", "72B-026.99", "Xe Khách", "Trắng", "0924272829", "80.000-120.000đ", "Thành Phố Hồ Chí Minh - Vũng Tàu", "20h30-22h30" , "20h30: Trạm xe thành phố:Số 03, Trần Phú, Phường 4, Quận 5, TP.HCM\n21h10: Văn Phòng Quán Toan\n21h20: Bến xe Hắc Dịch\n22h30: Văn Phòng Ngãi Giao", R.drawable.anh4, 30));
        listxe.add(new Xe(15, "Đồng Hương Sông Lam", "29B-004.89", "Xe Giường Nằm", "Vàng", "0969037123", "200.000-300.000đ", "Hà Nội - Vinh", "16h00-21h00","16h00: MĐ - 87 Dương Khuê:\n16h30: Bến Xe Nước Ngầm\n17h30: Hoàng Mai\n20h30: Nội thành TPVinh\n21h00: Thị xã Cửa Lò" ,R.drawable.anh5, 32));
        listxe.add(new Xe(16, "Duy Khang", "29B-413.09", "Xe Limousine", "Đen", "19003086", "150.000đ", "Hà Nội - Ninh Bình", "08h00-09h50","08h00:  VP Rạp Xiếc (79 Trần Nhân Tông)\n08h20:  Văn Phòng 93 Phố Vọng\n08h40: Công viên Cầu giấy\n09h35: Văn Phòng Ninh Bình \n09h50: BigC Ninh Bình (79 Trần Nhân Tông)" ,R.drawable.anh6, 11));
        listxe.add(new Xe(17, "Hùng Đức", "14F-004.27", "Xe Limousine", "Trắng", "0938466820", "140.000đ-170.000đ", "Quảng Ninh - Hà Nội", "09h30-12h00" , "09h30: Khu vực Cẩm Phả\n10h20: Ngã ba Cửa Ông\n11h40:Công Viên Hòa Bình\n12h00:Bến Xe Mỹ Đình", R.drawable.anh7, 16));
        listxe.add(new Xe(18, "Hoàng Long", "51B-088.80", "Xe Khách", "Xanh Lá Cây", "0942541785", "1.000.000đ", "Hà Nội - Sài Gòn", "06h00-18h00(1 ngày rưỡi)","06h00: Bến xe Nước Ngầm\n06h25: Bến xe Gia Lâm \n09h55: Ngã 3 Ông Đồn\n14h20: Trạm Xăng Dầu Huệ Thiên 3\n20h35: Cầu Đồng Nai\n18h00: Bến xe Miền Đông Mới" ,R.drawable.anh8, 40));
        listxe.add(new Xe(19, "Thiên Kim", "86F-020.37", "Xe Limousine", "Trắng", "0965480636", "230.000-280.000đ", "Sài Gòn Bình Thuận", "07h00-09h45","07h00:  Sân bay Tân Sơn Nhất\n07h35: Vòng xoay Điện Biên Phủ\08h45: Xã Đức Hạnh\n09h20:  Cây xăng Thanh Lợi 2\n09h45: Bến xe Bắc Ruộng" ,R.drawable.anh9, 12));
        listxe.add(new Xe(20, "Xuân Quỳnh", "37B-014.64", "Xe Khách", "Xanh Dương", "0984268240", "150.000đ", "Hà Nội- Hải Phòng", "08h00-09h50" , "08h00: Bến xe Mỹ Đình\n08h35: Văn phòng An Lão\n09h00: Văn phòng Toan\n09h50: Bến xe Thượng Lý", R.drawable.anh10, 24));
        listxe.add(new Xe(21, "Minh Long", "29F-014.50", "Xe Limousine", "Đen", "0954612543", "180.000đ", "Ninh Bình - Hà Nội", "14h00-16h00" , "14h00: Văn phòng Tam Điệp\n14h25: Tam Điệp\n14h40: Xã Đông Sơn\n15h40: Nội Thành Hà Nội \n016h00: Chung Cư CC2 khu đô thị Đồng Tàu", R.drawable.anh21, 20));
        listxe.add(new Xe(22, "GoodmorningCatBa", "15B-003.64", "Xe Khách", "Trắng", "0988745751", "150.000đ", "Hải Phòng - Hà Nội", "10h00-12h00" , "10h00: Trung tâm thị trấn Cát Bà\n10h35: Văn phòng Quán Toan\n10h55: Văn phòng An Lão\n15h50: Bến xe Mỹ Đình", R.drawable.anh22, 36));
        listxe.add(new Xe(23, "Hải Âu", "15B-047.76", "Xe Giường Nằm", "Trắng", "0982698820", "100.000-120.000đ", "Hải Phòng - Hà Nội", "16h30-19h00" , "16h30: Văn Phòng An Lão\n16h50: Bến xe Thượng Lý\n17h05: Văn Phòng Quán Toan\n17h20: Bến xe Vĩnh Niệm\n18h30: Bến xe Gia Lâm\n18h50: Bến xe Nước Ngầm", R.drawable.anh1, 24));
        listxe.add(new Xe(24, "Duy Khang", "29B-447.09", "Xe Limousine", "Đen", "19003086", "150.000đ", "Ninh Bình - Hà Nội", "08h00-09h45","08h00:BigC Ninh Bình\n08h15: Văn Phòng Ninh Bình\n09h00: Công viên Cầu giấy\n09h10: Văn Phòng 93 Phố Vọng \n09h30: VP Rạp Xiếc (79 Trần Nhân Tông)" ,R.drawable.anh6, 11));
        listxe.add(new Xe(25, "Tùng Tuấn", "14F-044.36", "Xe Limousine", "Trắng", "0914246820", "150.000đ-180.000đ", "Quảng Ninh - Hà Nội", "04h30-8h00" , "04h30: Legacy Yên Tử\n05h00: Ngã ba Cửa Ông\n07h40:Nhà Hát Lớn Hà Nội\n08h00:Bến Xe Mỹ Đình", R.drawable.anh25, 25));
        for(Xe x: listxe){
            sqlXe.AddXe(x);
        }
    }

    public void OpenSignUp(){
        Intent intent = new Intent( LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void Login(User s){
        Intent intent = new Intent( LoginActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_USER, s.getAccount());
        startActivity(intent);
        finish();
    }

    public void OpenForgotPassword(){
        Intent intent = new Intent( LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
    }


}
