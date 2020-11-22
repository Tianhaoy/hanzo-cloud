##github 
client_id ：abe1a42b59e3aba3e164
clientSecret：6129bbd5c6a95d67c3ed9887c9d83346504b5dee

code = fb23550376f978c3d357

//第一步
 https://github.com/login/oauth/authorize?client_id=abe1a42b59e3aba3e164&redirect_uri=http://localhost:6301/social/callback/github
    回调http://localhost:6301/social/callback/github?code=e189c28f58e69926b453
    
//第二步
 https://github.com/login/oauth/access_token
 参数
     client_id  abe1a42b59e3aba3e164
     client_secret  6129bbd5c6a95d67c3ed9887c9d83346504b5dee
     code       e189c28f58e69926b453
 返回
    access_token=50e9ba59bd04a925dff87fcc476a6a89bacf4e67&scope=&token_type=bearer
 
//第三步
 https://api.github.com/user   get
    Authorization   token 50e9ba59bd04a925dff87fcc476a6a89bacf4e67   
    Content-Type    application/json
返回
{
    "login": "Tianhaoy",
    "id": 45312497,
    "node_id": "MDQ6VXNlcjQ1MzEyNDk3",
    "avatar_url": "https://avatars3.githubusercontent.com/u/45312497?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/Tianhaoy",
    "html_url": "https://github.com/Tianhaoy",
    "followers_url": "https://api.github.com/users/Tianhaoy/followers",
    "following_url": "https://api.github.com/users/Tianhaoy/following{/other_user}",
    "gists_url": "https://api.github.com/users/Tianhaoy/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/Tianhaoy/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/Tianhaoy/subscriptions",
    "organizations_url": "https://api.github.com/users/Tianhaoy/orgs",
    "repos_url": "https://api.github.com/users/Tianhaoy/repos",
    "events_url": "https://api.github.com/users/Tianhaoy/events{/privacy}",
    "received_events_url": "https://api.github.com/users/Tianhaoy/received_events",
    "type": "User",
    "site_admin": false,
    "name": null,
    "company": "2469653218@qq.com",
    "blog": "http://blog.babehome.com",
    "location": "杭州",
    "email": null,
    "hireable": null,
    "bio": null,
    "twitter_username": null,
    "public_repos": 13,
    "public_gists": 0,
    "followers": 1,
    "following": 8,
    "created_at": "2018-11-24T13:25:39Z",
    "updated_at": "2020-11-20T01:44:38Z"
}