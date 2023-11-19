package com.example.du_an_1.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.controller.view.BillActivity;
import com.example.du_an_1.controller.view.CustomerActivity;
import com.example.du_an_1.controller.view.NetworkChangeActivity;
import com.example.du_an_1.controller.view.ProductActivity;

import com.example.du_an_1.database.BillDao;
import com.example.du_an_1.database.ProductDao;
import com.example.du_an_1.databinding.FragmentHomeBinding;

import com.example.du_an_1.desgin_pattern.single_pantter.AccountSingle;
import com.example.du_an_1.desgin_pattern.single_pantter.CartShopSingle;
import com.example.du_an_1.funtions.MessengerManager;
import com.example.du_an_1.funtions.MoneyFormat;
import com.example.du_an_1.funtions.RequestPermissions;

import com.example.du_an_1.model.Bill;
import com.example.du_an_1.model.CartShop;
import com.example.du_an_1.model.Employee;
import com.example.du_an_1.model.Product;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentHomeBinding homeBinding;

    private NetworkChangeActivity networkChangeActivity;

    Employee employee = AccountSingle.getInstance().getAccount();

    float inventoryQuantity = 0;
    float exportQuantity = 0;
    private ArrayList<Bill> bill30;
    private ArrayList<Bill> billNow;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        checkNetwork();
        initView();
        return homeBinding.getRoot();
    }

    private void initView() {
        bill30 = new ArrayList<>();
        billNow = new ArrayList<>();
        RequestPermissions.requestReadImgGalleryCamera(requireContext());
        AdRequest adRequest = new AdRequest.Builder().build();
        homeBinding.adView.loadAd(adRequest);
        homeBinding.adView2.loadAd(adRequest);

        CartShopSingle cartShopSingle = CartShopSingle.getInstance();

        if (cartShopSingle.getProducts().size() == 0){
            homeBinding.cartSize.setVisibility(View.GONE);
        }else{
            homeBinding.cartSize.setVisibility(View.VISIBLE);
            homeBinding.cartSize.setText(cartShopSingle.getProducts().size() + "");
        }

        homeBinding.shortcut.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product", "addProduct");
                startActivity(intent);
            }
        });

        homeBinding.help.btnMessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessengerManager.openMessengerWithLink("https://www.facebook.com/messages/148593518345206",requireActivity());
            }
        });

        homeBinding.shortcut.createBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), BillActivity.class);
                intent.putExtra("bill", "AddBill");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "addCustomer");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.customerManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "listCustomer");
                startActivity(intent);
            }
        });

        homeBinding.shortcut.customerCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CustomerActivity.class);
                intent.putExtra("customer", "categoryCustomer");
                startActivity(intent);
            }
        });
        statistical();
        barChart();

        homeBinding.iconCartShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), BillActivity.class);
                intent.putExtra("bill", "AddBill");
                startActivity(intent);
            }
        });

        homeBinding.help.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + "quanlykhohang204@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Cần giúp đỡ");
                intent.putExtra(Intent.EXTRA_TEXT, "Viết vấn đề của bạn vào đây");

                startActivity(Intent.createChooser(intent, "Choose an Email Client"));
            }
        });



        homeBinding.swipeRefresh.setOnRefreshListener(this);

        homeBinding.shortcut.productCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProductActivity.class);
                intent.putExtra("product", "categoryProduct");
                startActivity(intent);
            }
        });
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        statistical();
        barChart();
        homeBinding.swipeRefresh.setRefreshing(false);
    }

    private void statistical(){
        BillDao.bill30Day(employee.getIdShop(), new BillDao.GetData() {
            @Override
            public void getData(ArrayList<Bill> bills) {
                double totalRevenue = 0;
                double totalEx = 0;
                bill30.clear();

                bills.forEach(o -> {
                    if (o.getIdAccount().equals(employee.getName()+"-"+employee.getNumberPhone())){
                        bill30.add(o);
                    }
                });

                for (Bill bill : bill30){
                    totalRevenue += bill.getSumPrice();
                    for (CartShop cartShop: bill.getListProduct()){
                        totalEx += cartShop.getProduct().getCost();
                    }
                }

                homeBinding.tvTotalRevenue.setText(MoneyFormat.moneyFormat(totalRevenue));
                homeBinding.tvTotalExpenditure.setText(MoneyFormat.moneyFormat(totalEx));
                homeBinding.sumPrice.setText("Lợi nhuận: "+MoneyFormat.moneyFormat(totalRevenue - totalEx));
            }
        });

        BillDao.billDay(employee.getIdShop(), new BillDao.GetData() {
            @Override
            public void getData(ArrayList<Bill> bills) {
                double totalRevenue = 0;
                double totalEx = 0;
                billNow.clear();

                bills.forEach(o -> {
                    if (o.getIdAccount().equals(employee.getName()+"-"+employee.getNumberPhone())){
                        billNow.add(o);
                    }
                });

                for (Bill bill : billNow){
                    totalRevenue += bill.getSumPrice();
                    for (CartShop cartShop: bill.getListProduct()){
                        totalEx += cartShop.getProduct().getCost();
                    }
                }

                homeBinding.tvTotalRevenueDay.setText(MoneyFormat.moneyFormat(totalRevenue));
                homeBinding.tvTotalExpenditureDay.setText(MoneyFormat.moneyFormat(totalEx));
                homeBinding.sumPirceDay.setText("Lợi nhuận: "+MoneyFormat.moneyFormat(totalRevenue - totalEx));
            }
        });
    }

    private void barChart(){
        ArrayList<String> xValue = new ArrayList<>();
        xValue.add("Nhập");
        xValue.add("Xuất");
        xValue.add("Tồn");
        ArrayList<BarEntry> entries = new ArrayList<>();
        ProductDao.getProducts(employee.getIdShop(), new ProductDao.GetData() {
            @Override
            public void getData(ArrayList<Product> products) {
                if (inventoryQuantity == 0){
                    for (Product product: products){
                        inventoryQuantity += product.getQuantity();
                    }
                }
            }
        });
        BillDao.GetBills(employee.getIdShop(), new BillDao.GetData() {
            @Override
            public void getData(ArrayList<Bill> bills) {
                if (exportQuantity == 0){
                    for (Bill bill : bills){
                        exportQuantity += bill.getQuantity();
                    }
                }
            }
        });
        entries.add(new BarEntry(0,inventoryQuantity + exportQuantity));
        entries.add(new BarEntry(1,exportQuantity));
        entries.add(new BarEntry(2,inventoryQuantity));

        YAxis yAxis = new YAxis();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        BarDataSet barDataSet = new BarDataSet(entries,"Chú thích");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(barDataSet);

        homeBinding.barChart.setData(barData);

        homeBinding.barChart.getDescription().setEnabled(false);
        homeBinding.barChart.invalidate();

        homeBinding.barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValue));
        homeBinding.barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        homeBinding.barChart.getXAxis().setGranularity(1f);
        homeBinding.barChart.getXAxis().setGranularityEnabled(true);
    }

    public void checkNetwork (){
        networkChangeActivity = new NetworkChangeActivity(getContext());
        networkChangeActivity.startNetworkListener();
    }
}