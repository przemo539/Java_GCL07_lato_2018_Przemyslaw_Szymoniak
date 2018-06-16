 var value;
         var index = 1;
         var avaliable = false;
      function refresh(){
          console.log("test");
        //-----------------------------------------------------------------------
        // 2) Send a http request with AJAX <a href="http://api.jquery.com/jQuery.ajax/" target="_blank">http://api.jquery.com/jQuery.ajax/</a>
        //-----------------------------------------------------------------------
            $.ajax({
            url : 'http://localhost:8080/gallery/pictures/',
            dataType : 'json',
            success:function(data) {
                value = data;
                console.log(data);
                show_pictures(index);
              }
            });
        }
      refresh()
    function show_pictures(id){
        if((value['pictures']).length >0){
        //console.log(value['pictures']);
        id=(id%(value['pictures']).length)+1;
       console.log(id);
        $('#img_border').attr("src","http://localhost:8080/gallery/picture/"+id);
        $('#img_link').attr("href","http://localhost:8080/gallery/picture/"+id);
        $('#signature').html("<table><tr><td>Utworzono: </td><td>"+value['pictures'][id-1]['created']+"</td></tr>\n\
            <tr><td>Nazwa pliku:</td><td>"+value['pictures'][id-1]['name']+"</td></tr>\n\
            <tr><td>Rozdzielczosc:</td><td>"+value['pictures'][id-1]['resolution']+"</td></tr>\n\
            <tr><td>Rozmiar:</td><td>"+value['pictures'][id-1]['size']+"</td></tr></table>");
        avaliable = true;
    }else{
             $('#img_border').attr("src","#");
             $('#img_border').attr("alt","            Galeria pusta!            ");
             $('#img_link').attr("href","/#");
             $('#signature').html("");
             avaliable=false;
         }
    }
 $(function() {
$('#next').click(function(){
    if(avaliable){
        console.log("next");
        index++;
        show_pictures(index);
    }
    refresh();
});
$('#prev').click(function(){
    if(avaliable){
        console.log("prev");
        index--;
        show_pictures(index);
    }
    refresh();
});
 });
