function setFavicons(favImg){
    let headTitle = document.querySelector('head');
    let setFavicon = document.createElement('link');
    setFavicon.setAttribute('rel','shortcut icon');
    setFavicon.setAttribute('href',favImg);
    headTitle.appendChild(setFavicon);
}
setFavicons('https://javastudy.ru/wp-content/uploads/2015/04/Spring-logo.jpeg');

const userurl = '/api/user';

const authUser = fetch(userurl).then(response => response.json())
authUser.then(user => {
    let roles = [];
    for (let role of user.roles) {roles
        roles.push(" " + role.rolename
            .replaceAll("ROLE_", ""))
    }
        document.getElementById("navbar-email").innerHTML = user.email
        document.getElementById("navbar-roles").innerHTML = roles
    }
)
async function getUserPage() {
    let page = await fetch(userurl);

    if(page.ok) {
        let user = await  page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function  getInformationAboutUser(user) {
    const tableBody = document.getElementById('usertbody');
    let dataHtml = '';
    let roles = [];
    for (let role of user.roles) {roles
        roles.push(" " + role.rolename.toString()
            .replaceAll("ROLE_", ""))
    }
    dataHtml =
`<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles}</td>   
</tr>`

    tableBody.innerHTML = dataHtml;
}
getUserPage();