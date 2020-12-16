'use strict';

function getUserData(){
    // index.htmlの検索IDの値を取得
    const id = document.getElementById('id').value;

    //AjaxにてDemoControllerクラスのsearchメソッドを呼び出す
    let request = new XMLHttpRequest();
    request.open("get", "/search?id=" + id, true);
    request.send(null);
    request.onload = function (event) {
       // Ajaxが正常終了した場合
       if (request.readyState === 4 && request.status === 200) {
          // 該当するデータが無かった場合
          if(!request.responseText){
              alert("該当するデータはありませんでした");
              return;
          }
          // 該当するデータがあった場合は、取得したUserDataオブジェクトの内容を画面に表示
          // その際、名前・性別・メモはデコードする
          const userData = JSON.parse(request.responseText);
          document.getElementById('name').textContent = decodeURI(userData.name);
          document.getElementById('birthday').textContent = decodeURI(userData.birthday);

       // Ajaxが異常終了した場合
       }else{
          alert("エラーが発生し、データが取得できませんでした");
       }
    };
    // Ajaxが異常終了した場合
    request.onerror = function (event) {
       alert("エラーが発生し、データが取得できませんでした");
    }
}

function updateNameData(){
    // index.htmlの検索IDの値を取得
    const inputname = document.getElementById('inputname').value;
    
    //AjaxにてDemoControllerクラスのsearchメソッドを呼び出す
    let request = new XMLHttpRequest();
    request.open("get", "/update?inputname=" + inputname, true);
    request.send(null);
    request.onload = function (event) {
       // Ajaxが正常終了した場合
       if (request.readyState === 4 && request.status === 200) {
          // 該当するデータが無かった場合
          if(!request.responseText){
              alert("更新ができませんでした");
              return;
          }
		  alert("更新しました");
          
       // Ajaxが異常終了した場合
       }else{
          alert("更新ができませんでした");
       }
    };
    // Ajaxが異常終了した場合
    request.onerror = function (event) {
       alert("更新ができませんでした");
    }
}


function deleteUserData(){
    // index.htmlの検索IDの値を取得
    const inputid = document.getElementById('inputid').value;
    
    //AjaxにてDemoControllerクラスのsearchメソッドを呼び出す
    let request = new XMLHttpRequest();
    request.open("get", "/delete?inputid=" + inputid, true);
    request.send(null);
    request.onload = function (event) {
       // Ajaxが正常終了した場合
       if (request.readyState === 4 && request.status === 200) {
          // 該当するデータが無かった場合
          if(!request.responseText){
              alert("削除ができませんでした");
              return;
          }
		  alert("削除しました");
          
       // Ajaxが異常終了した場合
       }else{
          alert("削除ができませんでした");
       }
    };
    // Ajaxが異常終了した場合
    request.onerror = function (event) {
       alert("削除ができませんでした");
    }
}



