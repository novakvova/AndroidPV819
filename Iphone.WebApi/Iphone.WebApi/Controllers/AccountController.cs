﻿using Iphone.WebApi.DTO;
using Iphone.WebApi.Helpers;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace Iphone.WebApi.Controllers
{
    [Route("api/[controller]")]
    [Produces("application/json")]
    public class AccountController : ControllerBase
    {
        private UserDBContext _context;

        public AccountController(UserDBContext context)
        {
            _context = context;
        }

        [HttpPost]
        [Route("login")]
        public async Task<IActionResult> Login([FromBody]LoginDTO model)
        {
            Thread.Sleep(2000);
            if(!ModelState.IsValid)
            {
                var errors = CustomValidator.GetErrorsByModel(ModelState);
                return BadRequest(errors);
            }

            if(!_context.Logins.Any(x=> x.Email == model.Email))
            {
                var errors = CustomValidator.GetErrorsByModel(ModelState);
                return BadRequest();
            }
            return Ok(new
            {
                text="Ковбаса"
            });
        }
    }
}
