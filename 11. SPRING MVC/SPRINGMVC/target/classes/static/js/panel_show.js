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
        dataType : 'json'
    })
    .done(ret => {
        value = ret;
        console.log(ret);
        show_pictures(index);
    });
        
     } 
      refresh();
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
$('#delete').click(function(){
    if(avaliable){
        id=(index%(value['pictures']).length)+1;
        var pomoc = value['pictures'][id-1];
        $.ajax({
            url: 'http://localhost:8080/gallery/picture/'+id,
            type: 'DELETE',
           success: function(response) {               
                value['pictures'].splice($.inArray(pomoc, value),1);
                index++;
                show_pictures(index);
        }
}); 
    }
});
 });

// bind the on-change event
$(document).ready(function() {
  $("#upload-file-input").on("change", uploadFile);
});

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
  $.ajax({
    url: "/gallery/uploadFile",
    type: "POST",
    data: new FormData($("#upload-file-form")[0]),
    enctype: 'multipart/form-data',
    processData: false,
    contentType: false,
    cache: false,
    success: function () {
      alert('Obrazek zostanie załadowany w przeciagu sekundy');
    },
    error: function () {
      alert('nieok!');
    }
  });
} // function uploadFile