export default function authHeader() {
  let user = JSON.parse(localStorage.getItem('user'));

  if (user && user.accessToken) {
    return { 'X-AUTH-TOKEN': user.accessToken };
  } else {
    return {};
  }
}
