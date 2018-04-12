/**
 * 
 */
$(function(){
	
var gv=$("#dg");
var today=new Date();

//动态生成列
var dynamicCols=[];
var dynamicItems=[];
var dynamicItemsAfter=[];
var dynamicItemsEnd=[];

//设置时间
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

//以开始时间为基准设置时间

function GetDateStrA(AddDayCount) {
    
    //得到开始时间
    
    var dd = new Date($("#StartTime").datebox('getValue'));
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

$(function(){
    $("#StartTime").datebox('setValue',GetDateStr(-1));
    $("#EndTime").datebox('setValue',GetDateStr(0));
    
    //绑定空数据
    gv.datagrid( "dataBind" ,
            {
                autoHeight : 46 ,
                datas      : [],
                striped:true,
                pagination : true,
                autoRowHeight:false
                
            });
    //绑定空列
    gv.datagrid({
        columns:[[
                  {title:'时间',width:150,sortable:true},
                  {title:'站点1',width:150,sortable:true},
                  {title:'站点2',width:150,sortable:true},
                  {title:'站点3',width:500,sortable:true},
                  ]]
        
    });
    
});

//列元素(动态)
//序号
var stcd={
           field: 'Stcd',
           title: '序号',
          // width: 150,
           rowspan:3,
           sortable:true
}

//站名
var stnm={
       field: 'Stnm',
       title: '站名',
      // width: 150,
       rowspan:3,
       sortable:true
}





//平均值
var avg={
        title:'平均值',
        //width:500,
        colspan:2
}

//库水位
var rz={
    title:'库水位',
    //width:150 /*PS:动态列中不适宜固定宽度，其他同样的*/
}

//库容
var rv={
        title:'库容',
        width:150
}


//查询按钮
$("#btnSearch").click(function(){
    

    //得到开始日期和结束日期之间的天数之差
    var ipt1=$("#StartTime").datebox('getValue');
    var ipt2=$("#EndTime").datebox('getValue');
    
    var arr1 = ipt1.split("-"); 
    var arr2 = ipt2.split("-"); 
    
    var dt1 = new Date(); 
    dt1.setFullYear(arr1[0]); 
    dt1.setMonth(arr1[1] - 1); 
    dt1.setDate(arr1[2]);  
    var dt2 = new Date(); 
    dt2.setFullYear(arr2[0]);  
    dt2.setMonth(arr2[1] - 1); 
    dt2.setDate(arr2[2]);  
    var dif = dt2.getTime() - dt1.getTime(); 
    var days = dif / (24 * 60 * 60 * 1000); 
    
    
    
    //再次初始化，避免数组的堆积
     dynamicCols=[];
     dynamicItems=[];
     dynamicItemsAfter=[];
     dynamicItemsEnd=[];

    
    //前部
    dynamicItems.push(stcd);
    dynamicItems.push(stnm);
    
    //查询条件数据
    var datas={
            "Stcd":$("#stnmCombo").combobox('getValue'),
            "StartTime":$("#StartTime").datebox('getValue'),
            "EndTime":$("#EndTime").datebox('getValue')            
    }
    //查询具体的数据
    formLoader.load("querydata",datas,function(s){
        
        //空白SPAN
        var blank={
                title:' ',
                colspan:days*2+4 //动态得到COLSPAN的跨度，根据天数
                
        }
        dynamicItems.push(blank);
        
        //动态载入库水位数据


        
           
            
            //创建一个新的存储MAP中的键的东西（表头Field） -水位
            var index=[];
            $.each(s[0].DynamicList, function(i,option){
                index.push(i);
                
            });
          
            //创建一个新的存储MAP中的键的东西（表头Field） -流量
            var indexQ=[];
            $.each(s[0].DynamicListQ, function(i,option){
                indexQ.push(i);
                
            });
            
           
            
            
           //拼凑表头
            $.each(s[0].DynamicList, function(i,option){
                
                
                 //详细数据
                   var k=0; //设定一个值，匹配Z0，Z1，Z2。。。。。
                   
                   var d = new Date($("#StartTime").datebox('getValue'));
                   do{
                       //alert(i.substring(1));
                       d.setDate(d.getDate()+(parseInt(i.substring(1)))/2); //转换成INT类型的
                       
                       var RealDate=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate(); //动态得到日期值
                       
                       var details2={
                           title:RealDate,
                       //    width:150,
                           colspan:2
                               
                       }
                       
                       dynamicItemsAfter.push(details2);
                      k+=2;
                      break; //这里因为会执行i此结果 ，所以 BREAK掉
                   }while(days>k)

            });
            
             //把水位和流量合并成一个对象
            var extend=function(o,n,override){
                   for(var p in n)
                   {
                       //alert(n[p]);
                       
                       if(n.hasOwnProperty(p) && (!o.hasOwnProperty(p) || override))
                       {
                           
                           o[p]=n[p]; 
                       }
                           
                   }
                       
                };
            extend(s[0].DynamicList,s[0].DynamicListQ);
            
            //表头排序
            /*表头排序的作用是将2个不相干的MAP组合到一起，然后按照第二个数字的大小进行冒泡排序*/
            var aa=Object.keys(s[0].DynamicList);
            var bb=[];
            
//            for(var j=0;j<aa.length;j++)
//            {
//               var temp;
//
//               if(j+1==aa.length)
//               {
//                   break;
//               }
//               
////               alert(aa[j]);
////               alert(aa[j+1]);
//               
//               if(Number(aa[j].substring(1))>Number(aa[j+1].substring(1)))
//               {
//
//                   temp=aa[j];
//                   aa[j]=aa[j+1];
//                   aa[j+1]=temp;
//               }
//                
//
//            }
            
            //进行排序，因为是从第二个字母开始比较大小的，所以截取字符串长度
            aa.sort(function(a,b){return Number(a.substring(1))>Number(b.substring(1))?1:-1});
            //alert(aa);
          //alert(aa);
            //alert(aa.sortable());
            //再次PUSH表头列
            var indexAll=[];
            $.each(s[0].DynamicList, function(i,option){
                indexAll.push(i);
            });
            
            
            //alert(aa);
            //alert(Object.Keys());
            var z=Object.keys(s[0].DynamicList);
            var q=Object.keys(s[0].DynamicListQ);
            
            //这里的s指的是下标0,1,2,3,m指的是对应的z0,q1,z2,q3等等
            $(aa).each(function(s,m){
                
                   for(var j=0;j<index.length;j++)
                   {
//                       if(j<z.length)
//                       {
                          if(m==index[j])
                        {
                           //alert(index[j]);
                           var mk=index[j]; 
                           
                            //库水位
                           rz={
                            title:'水位',
                            field:"DynamicList"+mk, //切记，这里的field不能相同，每次循环都需要创建一个新的FIELD
                            formatter:function(value,row,index){
                                //alert(row);
                                //return value.z1; //因为这是一个对象DynamicList，所以返回对象的值
                                //alert(value);

                                
                            var temp=[];
                            
                            var count=0;
                            var final;
                            $.each(row.DynamicList,function(i,option){
                                
                                    temp[i]=bibao(option);
                                

                                    });
                            
                            //alert(temp["z0"]());
                            return temp[mk]();
                            //return temp;
                            }    
                        }
                           dynamicItemsEnd.push(rz);
                        }
                           if(m==indexQ[j])
                            {
                               
                            
                           var mk2=indexQ[j];
                           
                           //流量（动态值）
                            rv={
                                    title:'流量',
                                    field:"DynamicListQ"+mk2,
                                    formatter:function(value,row,index)
                                    {
                                        var temp2=[];
                                        $.each(row.DynamicListQ,function(i,option){
                                                //alert(option);

                                            temp2[i]=bibao(option); //闭包法则
                                                
                                            });
                                        //alert(temp2[mk2]());
                                        return temp2[mk2]();
                                    }    
                            }
                            dynamicItemsEnd.push(rv);
                            }
                          
                          
                          
                          
                       }
                
            });
            
    

             //库水位(平均)
           var rzAvg={
                   title:'水位',
                   field:"AvgRz"}
           
             //流量(平均)
           var rvAvg={
                   title:'流量',
                   field:"AvgQ",
                   //width:500
                   formatter:function(value,row,index)
                   {
                       if(value=="NaN")
                       {
                           return "-";
                       }
                       else
                       {
                           return value;
                       }
                   }
           }
           
            dynamicItemsAfter.push(avg);
            dynamicItemsEnd.push(rzAvg);
            dynamicItemsEnd.push(rvAvg);
            
        dynamicCols.push(dynamicItems);
        dynamicCols.push(dynamicItemsAfter);
        dynamicCols.push(dynamicItemsEnd);
        
        //绑定动态列
        gv.datagrid({
            columns:dynamicCols,
    
        });      
        
        //获取动态表头
        var opts = $('#dg').datagrid('getColumnFields');
        colName_=[]; //全局变量
        
        var count=0.5; 
        var r = /^[-+]?\d*$/; //判断是否为整数
        
        for(i=0;i<opts.length;i++)
        {
            
            
            var col = $('#dg').datagrid( "getColumnOption" , opts[i] );
            
            var title=col.title;
            
            if(i>=2)
            {
                //奇数列必须和偶数列的值相同,也就是说水位和库容值相同。比如1月水位，1月库容，2月水位，2月库容。
                //如果i的值为奇数的话，那么比如使奇数列的值和上一个偶数列的值相同，也就是减一。
                //每次增量改为0.5，也就是2分之一，可以有效避免数字退后。
                //因为是从0开始计算的，所以下面的并不是i-3和i-4而是i-2,i-3
                

                //偶数列
                if(r.test(count)==true)
                {
                    if((i-2)%2==0)
                    {    
                        
                    title=col.title+"("+GetDateStrA(i-1-count)+")";
                    
                    }
                    else if((i-2)%2!=0)
                    {
                        title=col.title+"("+GetDateStrA(i-2-count)+")";
                        
                    }
                }
                //奇数列
                else
                {
                    if((i-2)%2==0)
                    {    
                        
                    title=col.title+"("+GetDateStrA(i-1-count-0.5)+")";
                    
                    }
                    else if((i-2)%2!=0)
                    {
                        title=col.title+"("+GetDateStrA(i-2-count-0.5)+")";
                        
                    }    
                    
                }

                count+=0.5;
                //最后一列算作平均值，不参与计算
                if(i==opts.length-2||i==opts.length-1)
                {
                    title=col.title+"(平均值)";
                }
                
            }
            
            colName_.push(title);
        }
        
        gv.datagrid( "dataBind" ,
                {
                    datas      : s,
                    striped:true,
                    pagination : true,
                    pageSize:15
                    
                });
           
        
        
    });
    
    //重置大小,动态重置
    if(days>11)
    {
        var realwidth=1700+125*(days-11)
        gv.datagrid("resize",{width:realwidth});
    }
    else
    {
        gv.datagrid("resize",{width:1360});
    }
    
    
});

//导出excel
$("#export").click(function(){
    
    var url= colName_;
    location.href = context.RootPath + context.ControllerPath + "/exportexcel?StartTime="+$("#StartTime").datebox('getValue')+"&EndTime="+$("#EndTime").datebox('getValue')+"&Stcd="+$("#stnmCombo").combobox('getValue')+"&ColName="+encodeURI(encodeURI(url));
    
});


//得到测站编码
var dataCount=0;
formLoader.load("getstnm","",function(data){
    
    data.unshift({Stnm : "全部" , Stcd : "" });
    $("#stnmCombo").combobox({
        data:data,
        valueField:"Stcd",
        textField:"Stnm",
        editable:false,
        //panelHeight:"auto",
        
        onLoadSuccess:function()
        {
            //alert('1');
            //alert(data[0].Stcd);
            $("#stnmCombo").combobox('setValue',data[0].Stcd);
            dataCount=data.length;
        
        },
        onShowPanel:function()
        {
            if(dataCount.length>10)
            {
                $(this).combobox('panel').height(251);
                
            }    
            
        }
    });
});

//闭包效应
function bibao(a){
    return function (){
        return a;
    };
}


});