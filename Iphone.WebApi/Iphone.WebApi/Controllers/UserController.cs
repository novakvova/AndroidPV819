using IPhone.Application.Account;
using IPhone.Application.User.Profile;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Threading.Tasks;

namespace Iphone.WebApi.Controllers
{
    
    public class UserController : BaseController
    {
        [HttpGet("profile")]
        [Authorize]
        public async Task<ActionResult<UserViewModel>> ProfileAsync()
        {
            UserProfileCommand userCommand = new UserProfileCommand
            {
                UserName = User.Claims
                .FirstOrDefault(x => x.Type == "username").Value
            };
            
            return await Mediator.Send(userCommand);
        }
    }
}
