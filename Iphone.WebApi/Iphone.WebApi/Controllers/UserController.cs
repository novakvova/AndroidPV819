using Iphone.WebApi.Helpers;
using IPhone.Application.Account;
using IPhone.Application.User.Profile;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IdentityModel.Tokens.Jwt;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace Iphone.WebApi.Controllers
{
    
    public class UserController : BaseController
    {
        private readonly IWebHostEnvironment _webHostEnvironment;
        public UserController(IWebHostEnvironment webHostEnvironment)
        {
            _webHostEnvironment = webHostEnvironment;
        }
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

        [HttpGet("users")]
        public async Task<ActionResult<List<UserViewModel>>> UsersAsync()
        {
            UserListCommand userCommand = new UserListCommand {              
            };
            return await Mediator.Send(userCommand);
        }
        [HttpPost("upload-image")]
        public async Task<ActionResult<List<UserViewModel>>> UsersAsync(UserUploadImageCommand command)
        {
            string filename = Guid.NewGuid().ToString() + ".jpg";
            string path = _webHostEnvironment.ContentRootPath + @"\ProfileImages";
            if (!Directory.Exists(path))
            {
                Directory.CreateDirectory(path);
            }
            path = path + @"\" + filename;
            if (command.Image == null)
            {
                return null;
            }
            if (command.Image.Length == 0)
            {
                return null;
            }


            using (Bitmap b = ImageWorker.Base64StringToBitmap(command.Image))
            {
                Bitmap savedImage = ImageWorker.CreateImage(b, 400, 360);
                if (savedImage != null)
                {
                    savedImage.Save(path, ImageFormat.Jpeg);
                    return null;
                }
                else
                {
                    return null;
                }
            };
            //UserListCommand userCommand = new UserListCommand
            //{
            //};
            //return await Mediator.Send(userCommand);
            return null;
        }
    }
}
